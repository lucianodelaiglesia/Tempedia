package com.ldelaiglesia.tempedia.presentation.temtem_detail.components.evolution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ldelaiglesia.tempedia.common.PortraitImage
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun TemtemEvolutionTree(
    temtem: TemtemDetail,
    viewModel: TemtemDetailViewModel
) {
    if (temtem.evolution.evolves){
        val evolutionTree = temtem.evolution.evolutionTree

        Row(modifier = Modifier.fillMaxSize()
            .padding(20.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = ColorPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Evolution Tree",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Black.copy(alpha = 0.7f))
                )
                Column (
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    evolutionTree!!.forEach { evolution ->
                        LaunchedEffect(key1 = evolution.number) {
                            viewModel.getTemtemPortrait(evolution.number)
                        }

                        val portraitUrls by viewModel.portraitUrls
                        val fromPortraitUrl = portraitUrls[evolution.number]

                        Row(horizontalArrangement = Arrangement.Center) {
                            PortraitImage(
                                url = fromPortraitUrl,
                                modifier = Modifier
                                    .height(128.dp)
                                    .weight(0.5f)
                            )
                        }

                        if (evolution.stage != evolutionTree.last().stage) {
                            val nextEvolution =
                                evolutionTree.find { it.stage == evolution.stage + 1 }
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ){
                                Column(
                                    modifier = Modifier.padding(4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (nextEvolution!!.type == "levels") {
                                        Text("Leveling: ${nextEvolution.level}", fontSize = 28.sp)
                                    } else {
                                        Text(nextEvolution.description!!, fontSize = 28.sp)
                                    }
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}