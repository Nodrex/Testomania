package com.earth.testomania.apis.opentdb.di

import com.earth.testomania.apis.opentdb.data.repository.OpenTdbRepoImpl
import com.earth.testomania.apis.opentdb.data.source.OpenTdbQuizApi
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
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
    fun provideOpenTDBApi(okHttpClient: OkHttpClient, moshi: Moshi): OpenTdbQuizApi =
        Retrofit.Builder().baseUrl(OPEN_TDB_API_BASE_URL).client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient()).build()
            .create(OpenTdbQuizApi::class.java)

    @Provides
    @Singleton
    fun provideOpenTDBRepo(repo: OpenTdbRepoImpl): OpenTdbRepo = repo

}

const val OPEN_TDB_API_BASE_URL = "https://opentdb.com/"
