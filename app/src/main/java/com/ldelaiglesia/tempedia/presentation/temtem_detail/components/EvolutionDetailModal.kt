package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ldelaiglesia.tempedia.domain.models.EvolutionDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolutionDetailModal(
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    selectedEvolution: EvolutionDetail
) {
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = onDismissRequest) {
            Box(modifier = Modifier.systemBarsPadding()) {
                selectedEvolution.let { evolution ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = evolution.name,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .align(Alignment.Center),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Evolution type: ${evolution.type}",
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Text(
                            text = "#${evolution.number}",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Column {
                            Text(
                                text = "Traits",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                            evolution.traits.forEach { trait ->
                                Text(
                                    text = trait,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                        Text(
                            text = "Evolves at level:${evolution.level}",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Evolves at trading: ${if (evolution.trading) "Yes" else "No"}",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Column {
                            Text(
                                text = "Trait Mapping",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                            evolution.traitMapping.forEach { (trait, value) ->
                                Text(
                                    text = "$trait -> $value",
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}