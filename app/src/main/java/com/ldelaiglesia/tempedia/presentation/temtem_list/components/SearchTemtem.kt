package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTemtem(
    searchText: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier
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
}