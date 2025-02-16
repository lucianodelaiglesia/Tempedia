package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

enum class DetailOptions {
    STATS, TECHNIQUES, EVOLUTION, LOCATION
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SectionsButtons(
    selectedOption: DetailOptions,
    onOptionSelected: (DetailOptions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(ColorPrimary, shape = RoundedCornerShape(24.dp))
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailOptions.entries.toTypedArray().forEach { option ->
                OptionButton(
                    text = option.name,
                    option = option,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
            }
        }
    }
}

@Composable
fun OptionButton(
    text: String,
    option: DetailOptions,
    selectedOption: DetailOptions,
    onOptionSelected: (DetailOptions) -> Unit
){
    Button(
        onClick = { onOptionSelected(option) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selectedOption == option) Background else Color.Transparent
        ),
        modifier = Modifier
            .widthIn(min = 100.dp)
            .alpha(if (selectedOption == option) 1f else 0.5f)
    ){
        Text(text.lowercase().replaceFirstChar{ it.uppercase() }, color = Color.White)
    }
}