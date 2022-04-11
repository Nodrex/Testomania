package com.earth.testomania.technical.data.source.remote.dto

import com.earth.testomania.technical.domain.model.TechQuiz

data class TechQuizDTO(
    val answers: AnswersDTO?,
    val category: String?,
    val correct_answer: String?,
    val correct_answers: CorrectAnswersDTO?,
    val description: String?,
    val difficulty: String?,
    val explanation: String?,
    val id: Int,
    val multiple_correct_answers: String?,
    val question: String?,
    val tags: List<TagDTO>?,
    val tip: Any?
) {
    fun toTechQuiz(): TechQuiz {
        return TechQuiz(
            id = id,
            question = question ?: "",
            category = category + "/" + tags?.joinToString(separator = "/"),
            explanation = explanation ?: "",
            //hasMultiAnswer = multiple_correct_answers?,

        )
    }
}