package com.ldelaiglesia.tempedia.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Temtem (
    @Expose @SerializedName("number") val number: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("types") val types: List<String>,
    @Expose @SerializedName("stats") val stats: Stats,
    @Expose @SerializedName("wikiPortraitUrlLarge") val portrait: String,
    @Expose @SerializedName("portraitWikiUrl") val portraitList: String
    )

data class Stats(
    @Expose @SerializedName("hp") val hp: Int,
    @Expose @SerializedName("sta") val sta: Int,
    @Expose @SerializedName("spd") val spd: Int,
    @Expose @SerializedName("atk") val atk: Int,
    @Expose @SerializedName("def") val def: Int,
    @Expose @SerializedName("spatk") val spatk: Int,
    @Expose @SerializedName("spdef") val spdef: Int,
    @Expose @SerializedName("total") val total: Int
    )