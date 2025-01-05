package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background
import com.ldelaiglesia.tempedia.presentation.ui.theme.MediumGray

@Composable
fun TemtemDetailScreen(
    viewModel: TemtemDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.temtemDetail?.let { temtemDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .background(Background)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = temtemDetail.portrait),
                                contentDescription = null,
                                contentScale = ContentScale.None,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .aspectRatio(0.5f)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "#${temtemDetail.number}",
                                modifier = Modifier.wrapContentSize(),
                                fontSize = 20.sp,
                                color = Color.LightGray
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = temtemDetail.name,
                                modifier = Modifier.wrapContentSize(),
                                fontSize = 48.sp,
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            temtemDetail.types.forEach { type ->
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
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Stats", fontSize = 32.sp, color = Color.White)
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        temtemDetail.stats.forEach { stat ->
                            when (stat.key.uppercase() != "TOTAL") {
                                true ->
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(0.3f),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            Text(
                                                text = stat.key.uppercase(),
                                                modifier = Modifier
                                                    .wrapContentSize(),
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
                                                modifier = Modifier
                                                    .wrapContentSize(),
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
                                                        MediumGray,
                                                        shape = RoundedCornerShape(4.dp)
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

                                false ->
                                    Row(
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
                                                modifier = Modifier
                                                    .wrapContentSize(),
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
            }
        }
        if (state.error.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}