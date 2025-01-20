package com.ldelaiglesia.tempedia.presentation.temtem_list.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ldelaiglesia.tempedia.common.StateError
import com.ldelaiglesia.tempedia.common.StateLoading
import com.ldelaiglesia.tempedia.presentation.Screen
import com.ldelaiglesia.tempedia.presentation.temtem_list.TemtemListViewModel
import com.ldelaiglesia.tempedia.presentation.temtem_list.components.FilterMenuContent
import com.ldelaiglesia.tempedia.presentation.temtem_list.components.SearchAndFilterTemtem
import com.ldelaiglesia.tempedia.presentation.temtem_list.components.TemtemListItem
import kotlinx.coroutines.launch

@Composable
fun TemtemListScreen(
    navController: NavController, viewModel: TemtemListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var searchText by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedTypes by viewModel.selectedTypes.collectAsState()
    val typeList by remember { viewModel.typeList }
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                FilterMenuContent(
                    selectedTypes = selectedTypes, onTypeSelected = { type, isChecked ->
                        val localSelectedTypes = if (isChecked) {
                            selectedTypes + type
                        } else {
                            selectedTypes - type
                        }
                        viewModel.filterByTypes(localSelectedTypes)
                    }, typeList = typeList
                )
            }
        }, content = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        Text(
                            text = "Tempedia",
                            fontSize = 28.sp,
                            color = Color.White,
                        )
                    }
                    SearchAndFilterTemtem(
                        searchText = searchText, onQueryChange = {
                            searchText = it
                            viewModel.searchTemtem(it)
                        }, modifier = Modifier.weight(7f),
                        openDrawer = { viewModel.openDrawer() }
                    )

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.data) { temtem ->
                                TemtemListItem(temtem = temtem,
                                    onItemClick = { navController.navigate(Screen.TemtemDetailScreen.route + "/${temtem.number}") })
                            }
                        }
                        if (state.error.isNotBlank()) {
                            StateError(state = state)
                        }
                        if (state.isLoading) {
                            StateLoading()
                        }
                    }
                }
            }
            LaunchedEffect(key1 = Unit) {
                viewModel.resetSearchAndFilters()
            }

            LaunchedEffect(key1 = Unit) {
                viewModel.openDrawerEvent.collect {
                    scope.launch { drawerState.open() }
                }
            }
        })
    }
}
