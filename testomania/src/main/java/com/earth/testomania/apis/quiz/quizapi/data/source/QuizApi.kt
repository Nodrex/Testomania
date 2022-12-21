package com.earth.testomania.apis.quiz.quizapi.data.source

import com.earth.testomania.apis.quiz.quizapi.data.source.remote.dto.TechQuizDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val QUIZ_API_KEY = "rQY0VtcCgMPYMPxEL9f8bxZdmM2gfogf6QhkuKZa"
const val QUIZ_API_PATH = "api/v1/questions"

interface QuizApi {

    @GET(QUIZ_API_PATH)
    suspend fun getQuizList(
        @Query("apiKey") apiKey: String = QUIZ_API_KEY,
        @Query("limit") questionCount: Int = 20,
        @Query("category") category: String? = null,
        @Query("tags") tags: String? = null
    ): Response<List<TechQuizDTO>>
}