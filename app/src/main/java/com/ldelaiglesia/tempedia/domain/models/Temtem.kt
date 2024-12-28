package com.ldelaiglesia.tempedia.domain.models

data class Temtem(
    val number: Int,
    val name: String,
    val portrait: String,
    val types: List<String>
)