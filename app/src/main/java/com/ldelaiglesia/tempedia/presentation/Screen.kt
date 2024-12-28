package com.ldelaiglesia.tempedia.presentation

sealed class Screen(val route: String) {
    data object TemtemListScreen : Screen("temtem_list_screen")
    data object TemtemDetailScreen : Screen("temtem_detail_screen")
}