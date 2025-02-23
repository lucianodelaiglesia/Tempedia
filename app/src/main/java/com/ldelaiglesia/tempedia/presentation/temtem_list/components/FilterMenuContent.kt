package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.presentation.temtem_list.TemtemListViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary
import com.ldelaiglesia.tempedia.presentation.ui.theme.DarkGray

@Composable
fun FilterMenuContent(
    selectedTypes: Set<String>,
    onTypeSelected: (String, Boolean) -> Unit,
    typeList: List<Type>,
    viewModel: TemtemListViewModel
) {
    val isAndFilter by viewModel.isAndFilter.collectAsState()
    val options = listOf("OR", "AND")
    var selectedOption by remember { mutableStateOf(if (isAndFilter) "AND" else "OR") }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(DarkGray)
            .requiredWidth(250.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Filter by type",
                    fontSize = 28.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    SingleChoiceSegmentedButtonRow(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        options.forEachIndexed { index, label ->
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),

                                onClick = {
                                    selectedOption = label
                                    viewModel.updateIsAndFilter(label == "AND")
                                },
                                selected = selectedOption == label,
                                colors = if (selectedOption == label) {
                                    SegmentedButtonDefaults.colors(
                                        activeContainerColor = ColorPrimary,
                                        activeContentColor = Color.White,
                                        inactiveContainerColor = DarkGray,
                                        inactiveContentColor = Color.White

                                    )
                                } else {
                                    SegmentedButtonDefaults.colors(
                                        activeContainerColor = DarkGray,
                                        activeContentColor = Color.White,
                                        inactiveContainerColor = DarkGray,
                                        inactiveContentColor = Color.White
                                    )
                                }
                            ) {
                                Text(label)
                            }
                        }
                    }
                }
            }
        }
        item {
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
                    Image(
                        painter = rememberAsyncImagePainter(model = type.icon),
                        contentDescription = type.name,
                        modifier = Modifier.size(48.dp)
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
}