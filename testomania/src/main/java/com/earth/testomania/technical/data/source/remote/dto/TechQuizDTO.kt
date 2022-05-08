package com.earth.testomania.technical.data.source.remote.dto

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
    val tip: Any?
)