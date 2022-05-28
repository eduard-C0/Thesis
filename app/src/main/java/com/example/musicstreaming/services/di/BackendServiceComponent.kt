package com.example.musicstreaming.services.di

import com.example.musicstreaming.services.BackendService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class BackendServiceScope

@Qualifier
internal annotation class NullableBackendService

@Qualifier
internal annotation class BackendService

@Qualifier
internal annotation class Url


@BackendServiceScope
@Component(modules = [BackendServiceModule::class])
internal interface BackendServiceComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@Url url: String): Builder

        @BindsInstance
        fun enableLogging(enable: Boolean): Builder

        fun build(): BackendServiceComponent
    }

    fun inject(backendService: BackendService)
}