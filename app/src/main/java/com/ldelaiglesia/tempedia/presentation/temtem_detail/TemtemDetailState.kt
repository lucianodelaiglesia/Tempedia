package com.ldelaiglesia.tempedia.presentation.temtem_detail

import com.ldelaiglesia.tempedia.common.TemtemState
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail

data class TemtemDetailState(
    override val isLoading: Boolean = false,
    override val data: TemtemDetail? = null,
    override val error: String = "",
    val portraitLoadingState: Boolean = false,
    val portraitError: String = ""
): TemtemState<TemtemDetail>