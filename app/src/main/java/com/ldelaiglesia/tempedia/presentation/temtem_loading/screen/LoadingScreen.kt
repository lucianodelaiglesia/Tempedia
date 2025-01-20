package com.ldelaiglesia.tempedia.presentation.temtem_loading.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ldelaiglesia.tempedia.presentation.temtem_list.TemtemListViewModel
import com.ldelaiglesia.tempedia.presentation.temtem_loading.components.LoadingImage

@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: TemtemListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LoadingImage()

    LaunchedEffect(key1 = state.isLoading) {
        if (!state.isLoading) {
            navController.navigate("temtem_list_screen") {
                popUpTo("loading_screen") { inclusive = true }
            }
        }
    }
}