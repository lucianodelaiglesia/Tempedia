package com.ldelaiglesia.tempedia.ui.temteminfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ldelaiglesia.tempedia.model.api.Temtem
import com.ldelaiglesia.tempedia.service.TemtemApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TemtemInfoViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl("https://temtem-api.mael.tech/api/")
        .build()

    private val service: TemtemApiService = retrofit.create(TemtemApiService::class.java)

    val temtemInfo = MutableLiveData<Temtem>()

    fun getTemtemInfo(number: Int) {
        val call = service.getTemtemInfo(number)

        call.enqueue(object : Callback<Temtem> {
            override fun onResponse(call: Call<Temtem>, response: Response<Temtem>) {
                response.body().let { temtem ->
                    temtemInfo.postValue(temtem)
                }
            }

            override fun onFailure(call: Call<Temtem>, t: Throwable) {
                call.cancel()
            }

        })
    }
}