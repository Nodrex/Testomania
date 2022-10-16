package com.earth.testomania.common.model

data class Quiz(
    val id: Int = 0,
    val question: String = "",
    val category: String = "", //should be both category/tag
    val image: ImageQuiz = ImageQuiz.None,
    val explanation: String = "",
    val answers: List<Answer> = emptyList()
) {
    val hasMultiAnswer: Boolean = answers.count { it.isCorrect } > 1
}

