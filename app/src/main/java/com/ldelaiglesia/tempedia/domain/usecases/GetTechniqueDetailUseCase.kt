package com.ldelaiglesia.tempedia.domain.usecases

import coil.network.HttpException
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.models.TechniqueDetail
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTechniqueDetailUseCase @Inject constructor(
    private val provider: TemtemProvider,
) {
    operator fun invoke(techniqueName: String): Flow<Resource<TechniqueDetail>> = flow {
        try {
            emit(Resource.Loading())

            val techniqueDetail = provider.getAllTechniquesDetails().find {
                it.name == techniqueName
            }

            emit(Resource.Success(techniqueDetail!!))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server"))
        }
    }
}