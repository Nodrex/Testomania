package com.earth.testomania.apis.opentdb.data.source.remote.dto

data class OpenTdbQuizDTO(
    val response_code: Int,
    val results: List<ResultDto>
)