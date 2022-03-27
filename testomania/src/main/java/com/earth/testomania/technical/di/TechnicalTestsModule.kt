package com.earth.testomania.technical.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named


private const val QUIZ_API_NAME = "QUIZ_API"
private const val QUIZ_API_BASE_URL = "https://quizapi.io/api/v1/questions"

@Module
@InstallIn(SingletonComponent::class)
class TechnicalTestsModule {

    @Provides
    @Singleton
    @Named(QUIZ_API_NAME)
    fun provideQuizAPIRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(QUIZ_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}