package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary
import com.ldelaiglesia.tempedia.presentation.ui.theme.DarkGray

@Composable
fun FilterMenuContent(
    selectedTypes: Set<String>,
    onTypeSelected: (String, Boolean) -> Unit,
    typeList: List<Type>
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 32.dp)
            .background(DarkGray)
            .requiredWidth(250.dp)
    ) {
        typeList.forEach { type ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    modifier = Modifier.size(56.dp),
                    checked = selectedTypes.contains(type.name),
                    onCheckedChange = { isChecked ->
                        onTypeSelected(type.name, isChecked)
                    }
                )
                Text(
                    text = type.name,
                    fontSize = 28.sp,
                    color = Color.White,
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp),
                color = ColorPrimary
            )
        }
    }
}