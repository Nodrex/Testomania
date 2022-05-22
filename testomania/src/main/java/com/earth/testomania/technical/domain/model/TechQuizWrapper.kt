package com.earth.testomania.technical.domain.model

data class TechQuizWrapper(
    val quiz: TechQuiz,
    val userSelectedAnswers: List<String> = emptyList(), //Answers, which should be drawn on next quiz visit by user
)