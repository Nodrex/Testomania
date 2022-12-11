package com.earth.testomania.apis.quizapi.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnswersDTO(
    val answer_a: String?,
    val answer_b: String?,
    val answer_c: String?,
    val answer_d: String?,
    val answer_e: String?,
    val answer_f: String?,
)