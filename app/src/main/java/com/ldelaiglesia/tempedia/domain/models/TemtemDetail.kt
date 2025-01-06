package com.ldelaiglesia.tempedia.domain.models

data class TemtemDetail(
    val number: Int,
    val name: String,
    val types: List<String>,
    val stats: Map<String, Int>,
    val portrait: String,
    val portraitList: String,
    val gameDescription: String
)