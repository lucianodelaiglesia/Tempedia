package com.ldelaiglesia.tempedia.presentation.temtem_list

import com.ldelaiglesia.tempedia.domain.models.Temtem

data class TemtemListState(
    val isLoading: Boolean = false,
    val temtemList: List<Temtem> = emptyList(),
    val error: String = ""
)