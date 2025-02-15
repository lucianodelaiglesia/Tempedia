package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.domain.models.EvolutionDetail
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun TemtemEvolutions(
    temtem: TemtemDetail,
    onShowBottomSheet: (Boolean) -> Unit,
    onEvolutionSelected: (EvolutionDetail) -> Unit,
    viewModel: TemtemDetailViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        if (temtem.evolution.evolves) {
            if (temtem.evolution.evolutionTree!!.last().stage > 3){
                TemtemMultipleEvolutionTree(temtem = temtem, viewModel = viewModel)
            } else {
                TemtemEvolutionTree(temtem = temtem, viewModel = viewModel)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (temtem.evolution.from != null) {
                val fromNumber = temtem.evolution.from.number
                LaunchedEffect(key1 = fromNumber) {
                    viewModel.getTemtemPortrait(fromNumber)
                }
                val portraitUrls by viewModel.portraitUrls
                val fromPortraitUrl = portraitUrls[fromNumber]
                val evolution = temtem.evolution.from

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = ColorPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onShowBottomSheet(true)
                            onEvolutionSelected(evolution)
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "From",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black.copy(alpha = 0.7f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .height(64.dp)
                                .weight(0.5f)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = fromPortraitUrl
                                ),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Image(
                                painter = painterResource(id = R.drawable.portrait_border_primary),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier.weight(0.5f)) {
                            Text(
                                temtem.evolution.from.name,
                                fontSize = 28.sp,
                                color = Color.White,
                            )
                        }
                    }
                }
            } else {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = ColorPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "From",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black.copy(alpha = 0.7f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "${temtem.name} doesn't have a previous state",
                            color = Color.White,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (temtem.evolution.to != null) {
                val toNumber = temtem.evolution.to.number
                LaunchedEffect(key1 = toNumber) {
                    viewModel.getTemtemPortrait(toNumber)
                }
                val portraitUrls by viewModel.portraitUrls
                val toPortraitUrl = portraitUrls[toNumber]
                val evolution = temtem.evolution.to

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = ColorPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onShowBottomSheet(true)
                            onEvolutionSelected(evolution)
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "To",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black.copy(alpha = 0.7f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .height(80.dp)
                                .weight(0.5f)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = toPortraitUrl
                                ),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Image(
                                painter = painterResource(id = R.drawable.portrait_border_primary),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier.weight(0.5f)) {
                            Text(
                                temtem.evolution.to.name,
                                fontSize = 28.sp,
                                color = Color.White,
                            )
                        }
                    }
                }
            } else {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = ColorPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "To",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black.copy(alpha = 0.7f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "${temtem.name} doesn't evolve",
                            color = Color.White,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = ColorPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = "${temtem.name} doesn't evolve",
                        color = Color.White,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
