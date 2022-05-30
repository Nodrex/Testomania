package com.earth.testomania.technical.domain.model

data class TechQuizWrapper(
    val quiz: TechQuiz,
    val userSelectedAnswers: MutableList<String> = mutableListOf(), //Answers, which should be drawn on next quiz visit by user
    var multiSelectionWasDone: Boolean = false
)