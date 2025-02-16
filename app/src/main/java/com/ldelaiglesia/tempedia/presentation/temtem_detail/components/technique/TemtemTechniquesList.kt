package com.ldelaiglesia.tempedia.presentation.temtem_detail.components.technique

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.presentation.temtem_detail.TemtemDetailViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun TemtemTechniquesList(
    temtem: TemtemDetail,
    onShowBottomSheet: (Boolean) -> Unit,
    onTechniqueSelected: (Technique) -> Unit,
    viewModel: TemtemDetailViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Techniques",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
        temtem.techniques.forEach { technique ->
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
                            onTechniqueSelected(technique)
                            onShowBottomSheet(true)
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
                                        fontSize = 12.sp,
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