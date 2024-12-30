package com.ldelaiglesia.tempedia.presentation.temtem_loading.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.presentation.temtem_list.TemtemListViewModel
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary
import com.ldelaiglesia.tempedia.presentation.ui.theme.TextYellow

@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: TemtemListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tempedia_logo_large),
            contentDescription = "Tempedia Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularProgressIndicator(
            color = ColorPrimary,
            trackColor = TextYellow
        )
    }
    LaunchedEffect(key1 = state.isLoading) {
        if (!state.isLoading) {
            navController.navigate("temtem_list_screen") {
                popUpTo("loading_screen") { inclusive = true }
            }
        }
    }
}