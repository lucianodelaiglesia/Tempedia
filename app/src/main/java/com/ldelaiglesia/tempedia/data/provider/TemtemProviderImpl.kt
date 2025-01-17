package com.ldelaiglesia.tempedia.data.provider

import com.ldelaiglesia.tempedia.data.remote.TemtemApiService
import com.ldelaiglesia.tempedia.data.remote.dto.toTemtem
import com.ldelaiglesia.tempedia.data.remote.dto.toTemtemDetail
import com.ldelaiglesia.tempedia.data.remote.dto.toType
import com.ldelaiglesia.tempedia.data.remote.dto.toItem
import com.ldelaiglesia.tempedia.data.remote.dto.toTechniqueDetail
import com.ldelaiglesia.tempedia.domain.models.Item
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail
import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import javax.inject.Inject

class TemtemProviderImpl @Inject constructor(
    private val api: TemtemApiService
) : TemtemProvider {
    override suspend fun getTemtemList(): List<Temtem> {
        return api.getTemtemList().map { it.toTemtem() }
    }

    override suspend fun getTemtemInfo(id: Int): TemtemDetail {
        return api.getTemtemInfo(id).toTemtemDetail()
    }

    override suspend fun getAllTypes(): List<Type> {
        return api.getAllTypes().map { it.toType() }
    }

    override suspend fun getAllItems(): List<Item> {
        return api.getAllItems().map { it.toItem() }
    }

    override suspend fun getAllTechniquesDetails(): List<TechniqueDetail> {
        return api.getAllTechniquesDetails().map { it.toTechniqueDetail() }
    }
}