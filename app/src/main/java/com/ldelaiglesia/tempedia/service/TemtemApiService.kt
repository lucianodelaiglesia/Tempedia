package com.ldelaiglesia.tempedia.service

import com.ldelaiglesia.tempedia.model.api.Temtem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TemtemApiService {
    @GET("temtems/{number}")
    fun getTemtemInfo(@Path("number") id: Int): Call<Temtem>

    @GET("temtems")
    fun getTemtemList(): Call<List<Temtem>>
}