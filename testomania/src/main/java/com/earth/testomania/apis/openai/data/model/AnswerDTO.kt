package com.earth.testomania.apis.openai.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnswerDTO(
    val answer: String
)
