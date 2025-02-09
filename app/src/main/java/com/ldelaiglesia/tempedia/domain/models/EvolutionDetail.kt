package com.ldelaiglesia.tempedia.domain.models

data class EvolutionDetail(
    val stage: Int,
    val number: Int,
    val name: String,
    val level: Int,
    val type: String,
    val trading: Boolean,
    val traits: List<String>,
    val traitMapping: Map<String, String>
)