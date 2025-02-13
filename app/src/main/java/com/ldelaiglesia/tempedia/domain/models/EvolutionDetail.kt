package com.ldelaiglesia.tempedia.domain.models

data class EvolutionDetail(
    val stage: Int = 0,
    val number: Int = 0,
    val name: String = "",
    val level: Int = 0,
    val type: String = "",
    val description: String? = "",
    val trading: Boolean = false,
    val traits: List<String> = listOf(),
    val traitMapping: Map<String, String> = mapOf()
)