package com.ldelaiglesia.tempedia.domain.provider

import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.domain.models.Type

interface TemtemProvider {
    suspend fun getTemtemList(): List<Temtem>
    suspend fun getTemtemInfo(id: Int): TemtemDetail
    suspend fun getAllTypes(): List<Type>
}