package com.earth.testomania.technical.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TechQuizItemWrapper(
    val quiz: TechQuiz,
    val selectedAnswers: MutableList<SelectedAnswer> = mutableListOf(),
    var multiSelectionWasDone: MutableState<Boolean> = mutableStateOf(false),
    var point: Int = 0
)