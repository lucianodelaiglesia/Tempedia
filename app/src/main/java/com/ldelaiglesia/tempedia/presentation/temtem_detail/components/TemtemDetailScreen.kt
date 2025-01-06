package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary
import com.ldelaiglesia.tempedia.presentation.ui.theme.Damage
import com.ldelaiglesia.tempedia.presentation.ui.theme.MediumGray
import com.ldelaiglesia.tempedia.presentation.ui.theme.Stamina

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemtemDetailScreen(
    viewModel: TemtemDetailViewModel = hiltViewModel()
) {
    val state = viewModel.temtemDetailState.value

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedTechnique by remember { mutableStateOf<Technique?>(null) }
    val selectedTechniqueDetails by snapshotFlow {
        viewModel.techniqueDetailState.value.techniqueDetail
    }.collectAsState(initial = null)

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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = temtemDetail.gameDescription,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
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
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Techniques",
                            fontSize = 32.sp,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        temtemDetail.techniques.forEach { technique ->
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(bottom = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = ColorPrimary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedTechnique = technique
                                            showBottomSheet = true
                                            viewModel.getTechniqueDetails(technique.name)
                                        },
                                    shape = RoundedCornerShape(10.dp),
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = technique.name,
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.weight(0.85f)
                                            )
                                            Box(
                                                modifier = Modifier.weight(0.15f),
                                                contentAlignment = Alignment.CenterEnd
                                            ) {
                                                if (technique.levels != null) {
                                                    Text(
                                                        text = "Lvl ${technique.levels}",
                                                        fontSize = 14.sp,
                                                        color = Color.LightGray
                                                    )
                                                } else Text(
                                                    text = "TC",
                                                    fontSize = 14.sp,
                                                    color = Color.LightGray
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
        }
        if (showBottomSheet) {
            ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                Box(modifier = Modifier.systemBarsPadding()) {
                    selectedTechniqueDetails?.let { techniqueDetail ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text(
                                text = techniqueDetail.name,
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
                                        text = techniqueDetail.type,
                                        fontSize = 24.sp,
                                        color = Color.White,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Image(
                                        painter = rememberAsyncImagePainter(model = techniqueDetail.classIcon),
                                        contentDescription = null,
                                        contentScale = ContentScale.None,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .weight(0.5f)
                                            .padding(2.dp)
                                            .clip(RoundedCornerShape(24.dp))
                                            .background(Damage),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = techniqueDetail.damage,
                                            fontSize = 24.sp,
                                            color = Color.White
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(0.5f)
                                            .padding(2.dp)
                                            .clip(RoundedCornerShape(24.dp))
                                            .background(Stamina),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = techniqueDetail.staminaCost,
                                            fontSize = 24.sp,
                                            color = Color.White,
                                        )
                                    }

                                }
                            }
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .align(Alignment.Center),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Hold: ${techniqueDetail.hold}",
                                        fontSize = 24.sp,
                                        color = Color.White,
                                        modifier = Modifier.weight(0.3f)
                                    )
                                    Spacer(modifier = Modifier.weight(0.4f))
                                    Image(
                                        painter = rememberAsyncImagePainter(model = techniqueDetail.priorityIcon),
                                        contentDescription = null,
                                        contentScale = ContentScale.None,
                                        modifier = Modifier.weight(0.2f)
                                    )
                                }
                            }

                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .alpha(0f)
                            )

                            Text(
                                text = "Description",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                            Text(
                                text = techniqueDetail.description,
                                fontSize = 18.sp,
                                color = Color.White
                            )

                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .alpha(0f)
                            )

                            Text(
                                text = "Effect",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                            Text(
                                text = techniqueDetail.targets,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Text(
                                text = techniqueDetail.effectText,
                                fontSize = 18.sp,
                                color = Color.White
                            )

                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .alpha(0f)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Synergy",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = techniqueDetail.synergy,
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            Text(
                                text = techniqueDetail.synergyText,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Row(modifier = Modifier.fillMaxWidth()) {
                                techniqueDetail.synergyEffects.forEach { effect ->
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(text = "Effect: ${effect.effect}")
                                        Text(text = "Type: ${effect.type}")
                                        Text(text = "Damage: ${effect.damage}")
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