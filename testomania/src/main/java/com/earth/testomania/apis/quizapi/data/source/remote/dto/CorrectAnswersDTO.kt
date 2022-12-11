package com.earth.testomania.apis.quizapi.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CorrectAnswersDTO(
    val answer_a_correct: String?,
    val answer_b_correct: String?,
    val answer_c_correct: String?,
    val answer_d_correct: String?,
    val answer_e_correct: String?,
    val answer_f_correct: String?,
)