package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.domain.models.Temtem

data class TemtemDto(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("portraitWikiUrl") val portrait: String,
    @SerializedName("types") val types: List<String>
)

fun TemtemDto.toTemtem(): Temtem {
    return Temtem(
        number = number,
        name = name,
        portrait = portrait,
        types = types
    )
}