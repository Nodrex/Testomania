package com.earth.testomania.technical.di

import com.earth.testomania.technical.data.repository.QuizRepositoryImpl
import com.earth.testomania.technical.data.source.remote.QuizApi
import com.earth.testomania.technical.domain.repository.QuizRepository
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
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
private const val QUIZ_API_BASE_URL = "https://quizapi.io/api/v1/questions/"

@Module
@InstallIn(SingletonComponent::class)
class TechnicalTestsModule {

    @Provides
    @Singleton
    //@Named(QUIZ_API_NAME)
    fun provideQuizApi(okHttpClient: OkHttpClient): QuizApi = Retrofit.Builder()
        .baseUrl(QUIZ_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(QuizApi::class.java)

    @Provides
    @Singleton
    fun provideQuizRepo(quizApi: QuizApi): QuizRepository = QuizRepositoryImpl(quizApi)

    @Provides
    @Singleton
    fun provideGetQuizListUseCase(quizRepository: QuizRepository) = GetQuizListUseCase(quizRepository)

}