package com.ldelaiglesia.tempedia.di

import com.ldelaiglesia.tempedia.common.Constants.BASE_URL
import com.ldelaiglesia.tempedia.data.provider.TemtemProviderImpl
import com.ldelaiglesia.tempedia.data.remote.TemtemApiService
import com.ldelaiglesia.tempedia.domain.provider.TemtemProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTemtemApi(): TemtemApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TemtemApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTemtemProvider(api: TemtemApiService): TemtemProvider {
        return TemtemProviderImpl(api)
    }
}