package com.earth.testomania.apis.openai.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteOpenAIDataSource @Inject constructor(moshi: Moshi) : OpenAIDataSource {

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer %token_here%")
                    .build()
            )
        }
        .build()

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build().create(OpenAIApi::class.java)

    override suspend fun getAnswer(question: String): String {
        return networkApi.askQuestion(question).message().toString()
    }
}
