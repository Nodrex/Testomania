package com.earth.testomania.opentdb

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OpenTdbModule {
    @Provides
    @Singleton
    fun provideQuizApi(okHttpClient: OkHttpClient, moshi: Moshi): OpenTdbQuizApi =
        Retrofit.Builder().baseUrl(API).client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(OpenTdbQuizApi::class.java)
}

private const val API = "https://opentdb.com/api.php"
