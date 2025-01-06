package com.ldelaiglesia.tempedia.domain.provider

import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.domain.models.Item
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail

interface TemtemProvider {
    suspend fun getTemtemList(): List<Temtem>
    suspend fun getTemtemInfo(id: Int): TemtemDetail
    suspend fun getAllTypes(): List<Type>
    suspend fun getAllItems(): List<Item>
    suspend fun getAllTechniquesDetails(): List<TechniqueDetail>
}