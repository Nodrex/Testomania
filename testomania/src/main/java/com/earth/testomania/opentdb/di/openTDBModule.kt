package com.earth.testomania.opentdb.di

import com.earth.testomania.opentdb.OpenTdbQuizApi
import com.earth.testomania.opentdb.OpenTdbRepo
import com.earth.testomania.opentdb.OpenTdbRepoImpl
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
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient()).build()
            .create(OpenTdbQuizApi::class.java)

    @Provides
    @Singleton
    fun bindOpenTDB(repo: OpenTdbRepoImpl): OpenTdbRepo = repo

}

private const val API = "https://opentdb.com/"
