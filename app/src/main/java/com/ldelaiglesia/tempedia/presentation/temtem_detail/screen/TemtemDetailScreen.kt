package com.ldelaiglesia.tempedia.presentation.temtem_detail.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ldelaiglesia.tempedia.common.StateError
import com.ldelaiglesia.tempedia.common.StateLoading
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.DetailOptions
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.SectionsButtons
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TechniqueDetailModal
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemBasicInfo
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemEvolutions
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemImageOrGif
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemStatsDetails
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemTechniquesList

@Composable
fun TemtemDetailScreen(
    viewModel: TemtemDetailViewModel = hiltViewModel()
) {
    val state = viewModel.temtemDetailState.value

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedTechnique by remember { mutableStateOf<Technique?>(null) }
    val selectedTechniqueDetails by snapshotFlow {
        viewModel.techniqueDetailState.value.data
    }.collectAsState(initial = null)
    var selectedOption by remember { mutableStateOf(DetailOptions.STATS) }

    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let { temtemDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    TemtemImageOrGif(temtem = temtemDetail)
                }
                item {
                    TemtemBasicInfo(temtem = temtemDetail)
                }
                item {
                    SectionsButtons { option ->
                        selectedOption = option
                    }
                }
                when (selectedOption) {
                    DetailOptions.STATS ->
                        item {
                            TemtemStatsDetails(
                                temtem = temtemDetail,
                                viewModel = viewModel
                            )
                        }

                    DetailOptions.TECHNIQUES ->
                        item {
                            TemtemTechniquesList(
                                temtem = temtemDetail,
                                onShowBottomSheet = { showBottomSheet = true },
                                onTechniqueSelected = { selectedTechnique = it },
                                viewModel = viewModel
                            )
                        }

                    DetailOptions.EVOLUTION ->
                        item {
                            TemtemEvolutions(
                                temtem = temtemDetail,
                                viewModel = viewModel
                            )
                        }

                    DetailOptions.LOCATION ->
                        item {
                            //TODO Locations composable
                        }
                }
            }
        }
        selectedTechniqueDetails?.let {
            TechniqueDetailModal(
                showBottomSheet,
                onDismissRequest = { showBottomSheet = false },
                it
            )
        }
        if (state.error.isNotBlank()) {
            StateError(state)
        }
        if (state.isLoading) {
            StateLoading()
        }
    }
}