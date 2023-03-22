package com.earth.testomania.apis.openai.data

import com.earth.testomania.apis.openai.data.model.AnswerDTO
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenAIApi {
    @POST("questions/completions")
    suspend fun askQuestion(@Query("question") question: String): Response<AnswerDTO>
}
