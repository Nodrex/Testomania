package com.earth.testomania.common.model

data class QuizUIState(
    val quiz: Quiz,
    val selectedAnswers: MutableList<SelectedAnswer> = mutableListOf(),
    var overallScore: Double = 0.0
)