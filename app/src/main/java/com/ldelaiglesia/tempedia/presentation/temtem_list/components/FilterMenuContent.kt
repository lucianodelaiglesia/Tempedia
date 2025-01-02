package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
            .background(DarkGray)
            .requiredWidth(250.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp, bottom = 25.dp)
        ) {
            Text(
                text = "Filter by type",
                fontSize = 28.sp,
                color = Color.White,
            )
        }
        typeList.forEach { type ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onTypeSelected(type.name, !selectedTypes.contains(type.name))
                    },
                verticalAlignment = Alignment.CenterVertically
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
                    fontSize = 20.sp,
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