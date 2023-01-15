package com.earth.testomania.apis.quiz.quizapi.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagDTO(
    val name: String?,
)