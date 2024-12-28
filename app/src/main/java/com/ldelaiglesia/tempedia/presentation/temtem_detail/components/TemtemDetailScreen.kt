package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel

@Composable
fun TemtemDetailScreen(
    viewModel: TemtemDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp)) {
        state.temtemDetail?.let { temtemDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(393.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = temtemDetail.portrait),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(393.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Number: ${temtemDetail.number}",
                                modifier = Modifier.wrapContentSize(),
                                fontSize = 28.sp,
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Types: ",
                                fontSize = 28.sp,
                                color = Color.White,
                                textAlign = TextAlign.Left
                            )
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
                    }
                }
                item {
                    temtemDetail.stats.forEach {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "${it.key.uppercase()}: ${it.value}",
                                modifier = Modifier.wrapContentSize(),
                                fontSize = 28.sp,
                                color = Color.White
                            )
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

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun TemtemDetailScreenPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "temtemDetail.name",
                        modifier = Modifier
                            .padding(16.dp)
                            .wrapContentSize(),
                        textAlign = TextAlign.Center,
                        fontSize = 44.sp,
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(393.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.portrait_border),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(393.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        Image(
                            painter = painterResource(id = R.drawable.portrait_border),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(393.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Number: temtemDetail.number",
                        modifier = Modifier
                            .padding(16.dp)
                            .wrapContentSize(),
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Types: temtemDetail.types",
                        modifier = Modifier
                            .padding(16.dp)
                            .wrapContentSize(),
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
