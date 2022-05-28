package com.example.musicstreaming.music.authentification.di

import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import com.example.musicstreaming.services.retrofit.RetrofitBackendServiceApi
import com.example.musicstreaming.services.retrofit.SafeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object AuthentificationModule {

    @Provides
    fun provideBackendService(): BackendService = BackendService()
}