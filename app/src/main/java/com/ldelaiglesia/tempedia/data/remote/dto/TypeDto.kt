package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.common.Constants.BASE_URL
import com.ldelaiglesia.tempedia.domain.models.Type

data class TypeDto(
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String
)

fun TypeDto.toType(): Type {
    return Type(
        name = name,
        icon = "$BASE_URL$icon"
    )
}