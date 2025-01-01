package com.ldelaiglesia.tempedia.data.remote

import com.ldelaiglesia.tempedia.data.remote.dto.ItemDto
import com.ldelaiglesia.tempedia.data.remote.dto.TemtemDetailDto
import com.ldelaiglesia.tempedia.data.remote.dto.TemtemDto
import com.ldelaiglesia.tempedia.data.remote.dto.TypeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TemtemApiService {

    @GET("api/temtems/{number}")
    suspend fun getTemtemInfo(@Path("number") number: Int): TemtemDetailDto

    @GET("api/temtems")
    suspend fun getTemtemList(): List<TemtemDto>

    @GET("api/types")
    suspend fun getAllTypes(): List<TypeDto>

    @GET("api/items")
    suspend fun getAllItems(): List<ItemDto>
}
