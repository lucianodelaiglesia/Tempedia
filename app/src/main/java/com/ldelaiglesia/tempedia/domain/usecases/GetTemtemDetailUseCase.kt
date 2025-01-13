package com.ldelaiglesia.tempedia.domain.usecases

import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetTemtemDetailUseCase @Inject constructor(
    private val provider: TemtemProvider,
    private val getAllTypesUseCase: GetAllTypesUseCase
) {
    operator fun invoke(number: Int): Flow<Resource<TemtemDetail>> = flow {
        try {
            emit(Resource.Loading())

            // Getting temtem info
            val temtem = provider.getTemtemInfo(number)

            // Getting types from use case
            val allTypesResult = getAllTypesUseCase().first {
                it is Resource.Success
            }
            val typeMap = allTypesResult.data?.associateBy { it.name }

            // Match temtem types names with icons
            val typeIcons = temtem.types.map { type ->
                val typeData = typeMap!!.values.find {
                    it.name == type
                }
                typeData!!.icon
            }
            // Copying new data class with icons
            val temtemWithIcons = temtem.copy(types = typeIcons)
            emit(Resource.Success(temtemWithIcons))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server"))
        }
    }
}