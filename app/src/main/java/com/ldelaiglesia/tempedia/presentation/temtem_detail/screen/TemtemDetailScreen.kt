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
import com.ldelaiglesia.tempedia.domain.models.EvolutionDetail
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.DetailOptions
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution.EvolutionDetailModal
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.SectionsButtons
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.technique.TechniqueDetailModal
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemBasicInfo
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution.TemtemEvolutionTree
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution.TemtemEvolutions
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemImageOrGif
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution.TemtemMultipleEvolutionTree
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution.TemtemNotEvolving
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemStatsDetails
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.technique.TemtemTechniquesList

@Composable
fun TemtemDetailScreen(
    viewModel: TemtemDetailViewModel = hiltViewModel()
) {
    val state = viewModel.temtemDetailState.value

    var showBottomSheetTechnique by remember { mutableStateOf(false) }
    var showBottomSheetEvolution by remember { mutableStateOf(false) }
    var selectedTechnique by remember { mutableStateOf<Technique?>(null) }
    val selectedTechniqueDetails by snapshotFlow {
        viewModel.techniqueDetailState.value.data
    }.collectAsState(initial = null)
    var selectedOption by remember { mutableStateOf(DetailOptions.STATS) }

    var selectedEvolution by remember { mutableStateOf<EvolutionDetail?>(null) }

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
                    SectionsButtons(
                        selectedOption = selectedOption,
                        onOptionSelected = { selectedOption = it }
                    )
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
                                onShowBottomSheet = { showBottomSheetTechnique = true },
                                onTechniqueSelected = { selectedTechnique = it },
                                viewModel = viewModel
                            )
                        }

                    DetailOptions.EVOLUTION -> {
                        if (temtemDetail.evolution.evolves) {
                            if (temtemDetail.evolution.evolutionTree!!.last().stage > 3) {
                                item {
                                    TemtemMultipleEvolutionTree(
                                        temtem = temtemDetail,
                                        viewModel = viewModel,
                                        onShowBottomSheet = { showBottomSheetEvolution = true },
                                        onEvolutionSelected = { selectedEvolution = it }
                                    )
                                }
                            } else {
                                item {
                                    TemtemEvolutionTree(temtem = temtemDetail, viewModel = viewModel)
                                }
                                item {
                                    TemtemEvolutions(
                                        temtem = temtemDetail,
                                        onShowBottomSheet = { showBottomSheetEvolution = true },
                                        onEvolutionSelected = { selectedEvolution = it },
                                        viewModel = viewModel
                                    )
                                }
                            }
                        } else {
                            item{
                                TemtemNotEvolving(temtem = temtemDetail)
                            }
                        }
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
                showBottomSheetTechnique,
                onDismissRequest = { showBottomSheetTechnique = false },
                it
            )
        }

        selectedEvolution?.let {
            EvolutionDetailModal(
                showBottomSheetEvolution,
                onDismissRequest = { showBottomSheetEvolution = false },
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