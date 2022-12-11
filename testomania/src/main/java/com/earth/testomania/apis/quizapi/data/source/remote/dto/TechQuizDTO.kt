package com.earth.testomania.apis.quizapi.data.source.remote.dto

import com.earth.testomania.apis.quizapi.data.source.remote.dto.AnswersDTO
import com.earth.testomania.apis.quizapi.data.source.remote.dto.CorrectAnswersDTO
import com.earth.testomania.apis.quizapi.data.source.remote.dto.TagDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TechQuizDTO(
    val answers: AnswersDTO?,
    val category: String?,
    val correct_answers: CorrectAnswersDTO?,
    val description: String?,
    val difficulty: String?,
    val explanation: String?,
    val id: Int,
    val multiple_correct_answers: String?,
    val question: String?,
    val tags: List<TagDTO>?,
    val tip: Any?,
)