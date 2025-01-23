package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun StatsOrTechniquesButtons(onOptionSelected: (DetailOptions) -> Unit) {
    var selectedOption by remember { mutableStateOf(DetailOptions.STATS) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(ColorPrimary, shape = RoundedCornerShape(24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    selectedOption = DetailOptions.STATS
                    onOptionSelected(selectedOption)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == DetailOptions.STATS) Background else Color.Transparent
                ), modifier = Modifier
                    .widthIn(min = 100.dp)
                    .alpha(if (selectedOption == DetailOptions.STATS) 1f else 0.5f)
            ) {
                Text(
                    "Temtem Info",
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    selectedOption = DetailOptions.TECHNIQUES
                    onOptionSelected(selectedOption)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == DetailOptions.TECHNIQUES) Background else Color.Transparent
                ),
                modifier = Modifier
                    .widthIn(min = 100.dp)
                    .alpha(if (selectedOption == DetailOptions.TECHNIQUES) 1f else 0.5f)
            ) {
                Text(
                    "Techniques",
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    selectedOption = DetailOptions.EVOLUTION
                    onOptionSelected(selectedOption)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == DetailOptions.EVOLUTION) Background else Color.Transparent
                ),
                modifier = Modifier
                    .widthIn(min = 100.dp)
                    .alpha(if (selectedOption == DetailOptions.EVOLUTION) 1f else 0.5f),
            ) {
                Text(
                    "Evolution",
                    color = Color.White
                )
            }
        }
    }
}

enum class DetailOptions {
    STATS, TECHNIQUES, EVOLUTION
}