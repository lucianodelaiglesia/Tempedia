package com.ldelaiglesia.tempedia.domain.models

data class TemtemEvolution(
    val evolves: Boolean,
    val type: String? = "",
    val level: Int? = 0,
    val trading: Boolean? = false,
    val from: EvolutionDetail? = null,
    val to: EvolutionDetail? = null,
)