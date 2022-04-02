package com.earth.testomania.tests.general.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class OptionDTO(
    val correct: Int,
    val id: Int,
    val option: String,
    val question_id: Int,
//    val timeline: Any // what is this
)