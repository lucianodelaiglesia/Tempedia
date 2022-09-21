package com.ldelaiglesia.tempedia.ui.temtemlist

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

class TemtemListViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://temtem-api.mael.tech/api/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    private val service: TemtemApiService = retrofit.create(TemtemApiService::class.java)

    val temtemList = MutableLiveData<List<Temtem>>()

    fun getTemtemList() {
        val call = service.getTemtemList()

        call.enqueue(object : Callback<List<Temtem>> {
            override fun onResponse(call: Call<List<Temtem>>, response: Response<List<Temtem>>) {
                response.body().let { list ->
                    temtemList.postValue(list)
                }
            }

            override fun onFailure(call: Call<List<Temtem>>, t: Throwable) {
                call.cancel()
            }

        })
    }
}