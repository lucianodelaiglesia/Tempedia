package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.MediumGray

@Composable
fun TemtemStatsDetails(temtem: TemtemDetail, viewModel: TemtemDetailViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Stats", fontSize = 32.sp, color = Color.White)
        }
        temtem.stats.forEach { stat ->
            when (stat.key.uppercase() != "TOTAL") {
                true -> Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(0.3f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = stat.key.uppercase(),
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Left
                        )
                    }
                    Box(
                        modifier = Modifier.weight(0.15f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "${stat.value}",
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Left
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.6f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(
                                    MediumGray, shape = RoundedCornerShape(4.dp)
                                )

                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = stat.value / 120.toFloat())
                                    .height(20.dp)
                                    .background(
                                        viewModel.getStatColor(stat.value),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            )
                        }
                    }
                }

                false -> Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .weight(0.25f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = stat.key.uppercase(),
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Left
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .weight(0.75f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "${stat.value}",
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(10.dp),
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}