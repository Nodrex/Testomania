package com.earth.testomania.opentdb.models

data class OpenTdbQuizDto(
    val response_code: Int,
    val results: List<ResultDto>
)