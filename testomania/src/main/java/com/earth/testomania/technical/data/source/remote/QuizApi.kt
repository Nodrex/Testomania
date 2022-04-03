package com.earth.testomania.technical.data.source.remote

import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import retrofit2.Response
import retrofit2.http.GET

interface QuizApi {

    @GET(" -G -d apiKey=rQY0VtcCgMPYMPxEL9f8bxZdmM2gfogf6QhkuKZa -d limit=10")
    suspend fun getQuizList(): Response<List<TechQuizDTO>>

}