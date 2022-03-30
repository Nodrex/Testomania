package com.earth.testomania.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //TODO provide DB instance and so on
    @Provides
    @Singleton
    fun jsonSerializer(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

}