package com.ldelaiglesia.tempedia.presentation.temtem_detail

import com.ldelaiglesia.tempedia.common.TemtemState
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail

data class TechniqueDetailState(
    override val isLoading: Boolean = false,
    override val data: TechniqueDetail? = null,
    override val error: String = ""
): TemtemState<TechniqueDetail>