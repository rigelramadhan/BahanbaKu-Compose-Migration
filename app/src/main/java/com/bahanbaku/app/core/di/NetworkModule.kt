package com.bahanbaku.app.core.di

import com.bahanbaku.app.core.data.remote.retrofit.ApiConfig
import com.bahanbaku.app.core.data.remote.retrofit.ApiService
import com.bahanbaku.app.core.data.remote.retrofit.ApiServiceML
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    @Provides
    @Singleton
    fun provideApiServiceML(): ApiServiceML {
        return ApiConfig.getApiServiceML()
    }
}