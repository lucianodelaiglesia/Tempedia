package com.ldelaiglesia.tempedia.domain.models

import com.ldelaiglesia.tempedia.data.remote.dto.SynergyEffect

data class TechniqueDetail(
    val name: String,
    val type: String,
    val classIcon: String,
    val damage: String,
    val staminaCost: String,
    val hold: String,
    val priorityIcon: String,
    val synergy: String,
    val synergyEffects: List<SynergyEffect>,
    val targets: String,
    val description: String,
    val effectText: String,
    val synergyText: String
)