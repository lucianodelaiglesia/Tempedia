package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilterTemtem(
    searchText: String,
    onQueryChange: (String) -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        SearchBar(
            query = searchText,
            onQueryChange = onQueryChange,
            onSearch = onQueryChange,
            active = false,
            onActiveChange = {},
            placeholder = { Text("Search Temtem", fontSize = 18.sp, color = Color.Gray) },
            leadingIcon = { Icon(Icons.Rounded.Search, "Search", tint = Color.Gray) },
            modifier = modifier
        ) {}
        Button(
            onClick = openDrawer,
            modifier = Modifier
                .weight(3f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            )
        ) {
            FilterButton()
        }
    }
}