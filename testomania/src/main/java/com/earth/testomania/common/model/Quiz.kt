package com.earth.testomania.common.model

/**
 * @param image for now it is URL
 * @param category can be both category/tag
 */
data class Quiz(
    val id: Int = 0,
    val point: Double = 0.0,
    val question: String = "",
    val image: String = "",
    val category: String = "",
    val explanation: String = "",
    val answers: List<Answer> = emptyList(),
) {
    val correctAnswerCount = answers.count { it.isCorrect }
    val hasMultiAnswer = correctAnswerCount > 1
}
