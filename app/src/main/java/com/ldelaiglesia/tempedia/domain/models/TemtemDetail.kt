package com.ldelaiglesia.tempedia.domain.models

data class TemtemDetail(
    val number: Int,
    val name: String,
    val types: List<String>,
    val stats: Map<String, Int>,
    val portraitList: String,
    val gameDescription: String,
    val techniques: List<Technique>,
    val portrait: String,
    val portraitGif: String,
    val evolution: TemtemEvolution
)