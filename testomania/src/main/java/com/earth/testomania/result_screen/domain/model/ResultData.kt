package com.earth.testomania.result_screen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultData(
    val testIconRes: Int,
    val quizCategoryName: String,
    val overallProgress: Double = 0.0,
    val isTestDone: Boolean = false,
    val incorrectQuestions: List<IncorrectlyAnsweredQuizModel> = listOf()
) : Parcelable
