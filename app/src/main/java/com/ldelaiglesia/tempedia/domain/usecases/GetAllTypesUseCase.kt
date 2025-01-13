package com.ldelaiglesia.tempedia.domain.usecases

import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.models.Type
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import java.io.IOException

class GetAllTypesUseCase @Inject constructor(private val provider: TemtemProvider) {
    operator fun invoke(): Flow<Resource<List<Type>>> = flow {
        try {
            emit(Resource.Loading())
            val types = provider.getAllTypes()
            emit(Resource.Success(types))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server"))
        }
    }
}