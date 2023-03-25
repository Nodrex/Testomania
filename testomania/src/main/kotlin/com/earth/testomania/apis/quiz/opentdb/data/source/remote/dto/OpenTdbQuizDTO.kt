package com.earth.testomania.apis.quiz.opentdb.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenTdbQuizDTO(
    val response_code: Int,
    val results: List<ResultDTO>
)