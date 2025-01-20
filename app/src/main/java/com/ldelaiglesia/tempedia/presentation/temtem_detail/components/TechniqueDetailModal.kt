package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail
import com.ldelaiglesia.tempedia.presentation.ui.theme.Damage
import com.ldelaiglesia.tempedia.presentation.ui.theme.Stamina

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechniqueDetailModal(
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    selectedTechniqueDetails: TechniqueDetail
) {
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = onDismissRequest) {
            Box(modifier = Modifier.systemBarsPadding()) {
                selectedTechniqueDetails.let { techniqueDetail ->
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
                                        fontSize = 20.sp,
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
                                        fontSize = 20.sp,
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
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(0.5f)
                                ) {
                                    Text(
                                        text = "Effect: ${effect.effect}",
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Type: ${effect.type}",
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Damage: ${effect.damage}",
                                        fontSize = 14.sp,
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
}