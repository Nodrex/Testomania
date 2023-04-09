package com.earth.testomania.apis.quiz.opentdb.data.source

import com.earth.testomania.apis.quiz.opentdb.data.source.remote.dto.OpenTdbQuizDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTdbQuizApi {
    @GET("api.php")
    suspend fun getQuizList(
        @Query("amount") questionCount: Int = 20,
        @Query("category") category: Int = 0,
    ): Response<OpenTdbQuizDTO>
}

