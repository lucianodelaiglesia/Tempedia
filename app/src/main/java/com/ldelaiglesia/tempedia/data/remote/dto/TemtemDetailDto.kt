package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail

data class TemtemDetailDto(
    @SerializedName("number")
    val number: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("types")
    val types: List<String>,

    @SerializedName("stats")
    val stats: Stats,

    @SerializedName("wikiRenderStaticLumaUrl")
    val portrait: String,

    @SerializedName("portraitWikiUrl")
    val portraitList: String,

    @SerializedName("gameDescription")
    val gameDescription: String
)

data class Stats(
    @SerializedName("hp") val hp: Int,
    @SerializedName("sta") val sta: Int,
    @SerializedName("spd") val spd: Int,
    @SerializedName("atk") val atk: Int,
    @SerializedName("def") val def: Int,
    @SerializedName("spatk") val spatk: Int,
    @SerializedName("spdef") val spdef: Int,
    @SerializedName("total") val total: Int
)

fun TemtemDetailDto.toTemtemDetail(): TemtemDetail {
    return TemtemDetail(
        number = number,
        name = name,
        types = types,
        stats = mapOf(
            Pair("hp", stats.hp),
            Pair("sta", stats.sta),
            Pair("spd", stats.spd),
            Pair("atk", stats.atk),
            Pair("def", stats.def),
            Pair("spatk", stats.spatk),
            Pair("spdef", stats.spdef),
            Pair("total", stats.total)
        ),
        portrait = portrait,
        portraitList = portraitList,
        gameDescription = gameDescription
    )
}