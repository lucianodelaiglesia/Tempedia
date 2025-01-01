package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.domain.models.Item

data class ItemDto(
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("image") val image: String
)

fun ItemDto.toItem(): Item {
    return Item(
        name = name,
        icon = icon,
        image = image
    )
}