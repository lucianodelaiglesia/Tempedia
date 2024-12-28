package com.ldelaiglesia.tempedia.presentation.temtem_detail

import com.ldelaiglesia.tempedia.domain.models.TemtemDetail

data class TemtemDetailState(
    val isLoading: Boolean = false,
    val temtemDetail: TemtemDetail? = null,
    val error: String = ""
)