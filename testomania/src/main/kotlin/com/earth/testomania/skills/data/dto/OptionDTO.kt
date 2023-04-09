package com.earth.testomania.skills.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OptionDTO(
    val correct: Int,
    val id: Int,
    val option: String,
    val question_id: Int,
//    val timeline: Any // what is this
)