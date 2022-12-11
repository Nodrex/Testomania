package com.earth.testomania.result_screen.domain.model

import android.os.Parcelable
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.Quiz
import kotlinx.parcelize.Parcelize

@Parcelize
data class IncorrectlyAnsweredQuizModel(
    val quiz: Quiz,
    val correctAnswersList: List<Answer> = listOf(),
    val incorrectAnswersList: List<Answer> = listOf(),
) : Parcelable
