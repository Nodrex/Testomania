package com.earth.testomania.technical.data.source.remote

import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "rQY0VtcCgMPYMPxEL9f8bxZdmM2gfogf6QhkuKZa"

interface QuizApi {

    @GET("api/v1/questions")
    suspend fun getQuizList(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("limit") questionCount: Int = 20,
        @Query("category") category: String? = null,
        @Query("tags") tags: String? = null
    ): Response<List<TechQuizDTO>>

}