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

@Module
@InstallIn(SingletonComponent::class)
internal object BackendServiceModule {

    @Provides
    @BackendServiceScope
    fun provideNetworkClient(
        @Url url: String,
        enableLogging: Boolean
    ): NetworkClient {
        return NetworkClient(url, enableLogging)
    }

    @Provides
    @BackendServiceScope
    fun provideSafeRetrofit(networkClient: NetworkClient): SafeRetrofit =
        SafeRetrofit(networkClient)

    @Provides
    @BackendServiceScope
    @NullableBackendService
    fun provideNullableBackendServiceApi(safeRetrofit: SafeRetrofit): RetrofitBackendServiceApi? =
        safeRetrofit.getRetrofitService()

    @Provides
    @BackendServiceScope
    @BackendService
    fun provideRetrofitBackendServiceApi(proxy: RetrofitProxy): RetrofitBackendServiceApi {
        return Proxy.newProxyInstance(
            RetrofitBackendServiceApi::class.java.classLoader,
            arrayOf(RetrofitBackendServiceApi::class.java),
            proxy
        ) as RetrofitBackendServiceApi
    }

    @Provides
    @BackendServiceScope
    fun provideRetrofitBackendServiceImplementation(@BackendService backendServiceApi: RetrofitBackendServiceApi): RetrofitBackendService =
        RetrofitBackendServiceImplementation(backendServiceApi)

}