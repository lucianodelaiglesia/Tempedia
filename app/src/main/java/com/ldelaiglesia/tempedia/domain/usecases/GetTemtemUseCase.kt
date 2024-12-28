package com.ldelaiglesia.tempedia.domain.usecases

import coil.network.HttpException
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTemtemUseCase @Inject constructor(private val provider: TemtemProvider) {
    operator fun invoke(): Flow<Resource<List<Temtem>>> = flow {
        try {
            emit(Resource.Loading())
            val temtems = provider.getTemtemList()
            emit(Resource.Success(temtems))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server"))
        }
    }
}