package com.earth.testomania.skills.domain

data class GeneralMathematicalSkillsTest(
    val question: String,
    val possibleAnswers: List<PossibleAnswer>
)

data class PossibleAnswer(val text: String, val isCorrect: Boolean)