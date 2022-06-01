package com.earth.testomania.technical.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TechQuizWrapper(
    val quiz: TechQuiz,
    val userSelectedAnswers: MutableList<String> = mutableListOf(), //Answers, which should be drawn on next quiz visit by user
    var multiSelectionWasDone: MutableState<Boolean> = mutableStateOf(false),
    var point: Int = 0
)