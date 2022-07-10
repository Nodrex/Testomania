package com.earth.testomania.presentation.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IncorrectAnsweredQuestionModel(
    val id: String,
    val questionText: String,
    val correctAnswerIndex: String = "",
    val correctAnswerText: String,
    val choiceIndex: String = "",
    val choiceText: String,
    val description: String? = null
): Parcelable
