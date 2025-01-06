package com.ldelaiglesia.tempedia.presentation.temtem_detail

import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail

data class TechniqueDetailState(
    val isLoading: Boolean = false,
    val techniqueDetail: TechniqueDetail? = null,
    val error: String = ""
)