package com.ldelaiglesia.tempedia.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ldelaiglesia.tempedia.presentation.temtem_detail.components.TemtemDetailScreen
import com.ldelaiglesia.tempedia.presentation.temtem_list.components.TemtemListScreen
import com.ldelaiglesia.tempedia.presentation.temtem_loading.components.LoadingScreen
import com.ldelaiglesia.tempedia.presentation.ui.theme.TempediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContent {
            TempediaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoadingScreen.route
                    ) {
                        composable(
                            route = Screen.LoadingScreen.route
                        ) {
                            LoadingScreen(navController)
                        }
                        composable(
                            route = Screen.TemtemListScreen.route
                        ) {
                            TemtemListScreen(navController)
                        }
                        composable(
                            route = Screen.TemtemDetailScreen.route + "/{number}"
                        ) {
                            TemtemDetailScreen()
                        }
                    }
                }
            }
        }
    }
}