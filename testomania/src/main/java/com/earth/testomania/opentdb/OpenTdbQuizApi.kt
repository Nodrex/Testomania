package com.earth.testomania.opentdb

import com.earth.testomania.opentdb.models.OpenTdbQuizDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTdbQuizApi {
    @GET
    suspend fun getQuizList(
        @Query("amount") questionCount: Int = 20,
        @Query("category") category: Int = 0,
    ): Response<OpenTdbQuizDto>
}

