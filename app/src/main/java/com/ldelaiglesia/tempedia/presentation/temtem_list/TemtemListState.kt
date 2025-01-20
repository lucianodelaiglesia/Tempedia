package com.ldelaiglesia.tempedia.presentation.temtem_list

import com.ldelaiglesia.tempedia.common.TemtemState
import com.ldelaiglesia.tempedia.domain.models.Temtem

data class TemtemListState(
    override val isLoading: Boolean = false,
    override val data: List<Temtem> = emptyList(),
    override val error: String = ""
): TemtemState<List<Temtem>>