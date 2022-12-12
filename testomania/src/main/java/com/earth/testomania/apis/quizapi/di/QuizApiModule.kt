package com.earth.testomania.apis.quizapi.di

import com.earth.testomania.apis.quizapi.data.repository.QuizRepositoryImpl
import com.earth.testomania.apis.quizapi.data.source.QuizApi
import com.earth.testomania.apis.quizapi.domain.repository.QuizRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


const val QUIZ_API_BASE_URL = "https://quizapi.io/"

@Module
@InstallIn(SingletonComponent::class)
class TechnicalTestsModule {

    @Provides
    @Singleton
    fun provideQuizApi(okHttpClient: OkHttpClient, moshi: Moshi): QuizApi = Retrofit.Builder()
        .baseUrl(QUIZ_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(QuizApi::class.java)

    @Provides
    @Singleton
    fun provideQuizRepo(quizApi: QuizApi): QuizRepository = QuizRepositoryImpl(quizApi)

    /*  @Provides
      @Singleton
      fun provideGetQuizListUseCase(quizRepository: QuizRepository) =
          GetQuizListUseCase(quizRepository)*/

}