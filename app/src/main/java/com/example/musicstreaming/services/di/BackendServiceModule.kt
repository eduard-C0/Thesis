package com.example.musicstreaming.services.di

import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import com.example.network.NetworkClient
import com.example.musicstreaming.services.retrofit.RetrofitBackendServiceApi
import com.example.musicstreaming.services.retrofit.RetrofitBackendServiceImplementation
import com.example.musicstreaming.services.retrofit.RetrofitProxy
import com.example.musicstreaming.services.retrofit.SafeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Proxy
import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
internal annotation class NullableBackendService

@Qualifier
internal annotation class BackendService

@Module
@InstallIn(SingletonComponent::class)
internal object BackendServiceModule {

    @Provides
    fun provideNetworkClient(): NetworkClient =  NetworkClient("https://boiling-plains-24159.herokuapp.com/", true)

    @Provides
    fun provideSafeRetrofit(networkClient: NetworkClient): SafeRetrofit =
        SafeRetrofit(networkClient)

    @Provides
    @NullableBackendService
    fun provideNullableBackendServiceApi(safeRetrofit: SafeRetrofit): RetrofitBackendServiceApi? =
        safeRetrofit.getRetrofitService()

    @Provides
    @BackendService
    fun provideRetrofitBackendServiceApi(proxy: RetrofitProxy): RetrofitBackendServiceApi {
        return Proxy.newProxyInstance(
            RetrofitBackendServiceApi::class.java.classLoader,
            arrayOf(RetrofitBackendServiceApi::class.java),
            proxy
        ) as RetrofitBackendServiceApi
    }

    @Provides
    fun provideRetrofitBackendServiceImplementation(@BackendService backendServiceApi: RetrofitBackendServiceApi): RetrofitBackendService =
        RetrofitBackendServiceImplementation(backendServiceApi)

}