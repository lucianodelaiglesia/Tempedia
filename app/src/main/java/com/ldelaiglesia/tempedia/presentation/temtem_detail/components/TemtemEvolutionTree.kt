package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
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

        Row(modifier = Modifier.fillMaxSize()) {
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
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    evolutionTree!!.forEach { temtem ->
                        LaunchedEffect(key1 = temtem.number) {
                            viewModel.getTemtemPortrait(temtem.number)
                        }

                        //TODO: special distribution for those temtem that have more than 3 evolutions (Tuwai)

                        val portraitUrls by viewModel.portraitUrls
                        val fromPortraitUrl = portraitUrls[temtem.number]

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

                        if (temtem.stage != evolutionTree.last().stage) {
                            Column (
                                modifier = Modifier.padding(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                                if (temtem.type == "levels") {
                                    val evolvesAt = evolutionTree.find{
                                        it.stage == temtem.stage + 1
                                    }!!.level

                                    Text("Lvling: $evolvesAt")
                                } else {
                                    Text("special")
                                    Text(temtem.description!!)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}