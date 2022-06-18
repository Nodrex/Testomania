package com.earth.testomania.presentation.result

data class IncorrectAnsweredQuestionModel(
    val id: String,
    val questionText: String,
    val correctAnswerIndex: String = "",
    val correctAnswerText: String,
    val choiceIndex: String = "",
    val choiceText: String,
    val description: String? = null
)
