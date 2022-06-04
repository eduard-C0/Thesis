package com.example.musicstreaming.services.di

import android.content.Context
import android.content.SharedPreferences
import com.example.musicstreaming.services.retrofit.*
import com.example.musicstreaming.services.retrofit.RetrofitBackendServiceApi
import com.example.musicstreaming.services.retrofit.RetrofitBackendServiceImplementation
import com.example.musicstreaming.services.retrofit.RetrofitNapsterServiceApi
import com.example.musicstreaming.services.retrofit.RetrofitProxy
import com.example.musicstreaming.services.retrofit.SafeRetrofit
import com.example.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Proxy
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Qualifier
internal annotation class NullableBackendService

@Qualifier
internal annotation class BackendService

@Qualifier
internal annotation class BackendScope

@Qualifier
internal annotation class NapsterScope

@Qualifier
internal annotation class NullableNapsterService

@Qualifier
internal annotation class NapsterService

@Module
@InstallIn(SingletonComponent::class)
internal object BackendServiceModule {

    @Provides
    @BackendScope
    fun provideBackendNetworkClient(sharedPreferences: SharedPreferences): NetworkClient =  NetworkClient("https://boiling-plains-24159.herokuapp.com/", true, sharedPreferences)

    @Provides
    @BackendScope
    fun provideSafeRetrofit(@BackendScope networkClient: NetworkClient): SafeRetrofit =
        SafeRetrofit(networkClient)

    @Provides
    @NullableBackendService
    fun provideNullableBackendServiceApi(@BackendScope safeRetrofit: SafeRetrofit): RetrofitBackendServiceApi? =
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


    //Napster

    @Provides
    @NapsterScope
    fun provideNapsterNetworkClient(sharedPreferences: SharedPreferences): NetworkClient =  NetworkClient("https://api.napster.com/", true, sharedPreferences)

    @Provides
    @NapsterScope
    fun provideNapsterSafeRetrofit(@NapsterScope networkClient: NetworkClient): SafeRetrofit =
        SafeRetrofit(networkClient)

    @Provides
    @NullableNapsterService
    fun provideNullableNapsterServiceApi(@NapsterScope safeRetrofit: SafeRetrofit): RetrofitNapsterServiceApi? =
        safeRetrofit.getRetrofitNapsterService()

    @Provides
    @NapsterService
    fun provideRetrofitNapsterServiceApi(proxy: RetrofitNapsterProxy): RetrofitNapsterServiceApi {
        return Proxy.newProxyInstance(
            RetrofitNapsterServiceApi::class.java.classLoader,
            arrayOf(RetrofitNapsterServiceApi::class.java),
            proxy
        ) as RetrofitNapsterServiceApi
    }

    @Provides
    fun provideRetrofitNapsterServiceImplementation(@NapsterService napsterServiceApi: RetrofitNapsterServiceApi): RetrofitNapsterService =
        RetrofitNapsterServiceImplementation(napsterServiceApi)


    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("application", Context.MODE_PRIVATE)
    }
}