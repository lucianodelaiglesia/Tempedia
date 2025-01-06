package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.common.Constants.BASE_URL
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail

data class TechniqueDetailDto(
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("classIcon") val classIcon: String,
    @SerializedName("damage") val damage: String,
    @SerializedName("staminaCost") val staminaCost: String,
    @SerializedName("hold") val hold: String,
    @SerializedName("priorityIcon") val priorityIcon: String,
    @SerializedName("synergy") val synergy: String,
    @SerializedName("synergyEffects") val synergyEffects: List<SynergyEffect>,
    @SerializedName("targets") val targets: String,
    @SerializedName("description") val description: String,
    @SerializedName("effectText") val effectText: String,
    @SerializedName("synergyText") val synergyText: String
)

data class SynergyEffect(
    @SerializedName("damage") val damage: Int,
    @SerializedName("type") val type: String,
    @SerializedName("effect") val effect: String,
)

fun TechniqueDetailDto.toTechniqueDetail(): TechniqueDetail {
    return TechniqueDetail(
        name = name,
        type = type,
        classIcon = "$BASE_URL$classIcon",
        damage = damage,
        staminaCost = staminaCost,
        hold = hold,
        priorityIcon = "$BASE_URL$priorityIcon",
        synergy = synergy,
        synergyEffects = synergyEffects,
        targets = targets,
        description = description,
        effectText = effectText,
        synergyText = synergyText
    )
}