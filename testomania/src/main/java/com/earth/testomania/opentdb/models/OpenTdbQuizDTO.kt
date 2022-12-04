package com.earth.testomania.opentdb.models

data class OpenTdbQuizDTO(
    val response_code: Int,
    val results: List<ResultDto>
)