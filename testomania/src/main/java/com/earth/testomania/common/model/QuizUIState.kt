package com.earth.testomania.common.model

data class QuizUIState(
    val quiz: Quiz,
    //We need to know selected answers, to draw again when user visits previous quiz in ViewPager
    val selectedAnswers: MutableList<SelectedAnswer> = mutableListOf(),
    var overallScore: Double = 0.0
)