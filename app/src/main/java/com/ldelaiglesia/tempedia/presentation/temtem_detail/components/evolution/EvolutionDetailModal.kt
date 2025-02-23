package com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.domain.models.EvolutionDetail
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemTraits

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolutionDetailModal(
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    selectedEvolution: EvolutionDetail,
    viewModel: TemtemDetailViewModel
) {
    val evolutionNumber = selectedEvolution.number
    val evolutionTypes by snapshotFlow{
        viewModel.evolutionTypes.value
    }.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = evolutionNumber) {
        viewModel.getEvolutionTypes(evolutionNumber)
    }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = onDismissRequest) {
            Box(modifier = Modifier.systemBarsPadding()) {
                selectedEvolution.let { evolution ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterStart),
                            ) {
                                evolutionTypes.forEach { type ->
                                    Box(
                                        modifier = Modifier.size(64.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(model = type),
                                            contentDescription = null,
                                            modifier = Modifier.size(64.dp),
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                }
                            }
                            Text(
                                text = evolution.name,
                                fontSize = 32.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                            Text(
                                text = "#${evolution.number}",
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterEnd),
                                fontSize = 20.sp,
                                color = Color.LightGray,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (evolution.type == "levels") {
                                if (evolution.stage == 0) {
                                    Text(
                                        text = "Base stage",
                                        fontSize = 24.sp,
                                        color = Color.White
                                    )
                                } else {
                                    Text(
                                        text = "Evolution type: leveling ${evolution.level}",
                                        fontSize = 24.sp,
                                        color = Color.White
                                    )
                                }
                            } else {
                                Text(
                                    text = "Evolution type: ${evolution.type}",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "How: \n${evolution.description}",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                            }
                        }
                        TemtemTraits(
                            traits = evolution.traitMapping.keys.toList(),
                            text = "Traits:",
                            padding = 10
                        )
                        TemtemTraits(
                            traits = evolution.traitMapping.values.toList(),
                            text = "Comes from:",
                            padding = 10
                        )
                    }
                }
            }
        }
    }
}