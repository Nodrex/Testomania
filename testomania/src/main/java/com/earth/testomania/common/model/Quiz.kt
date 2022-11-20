package com.earth.testomania.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @param image for now it is URL
 * @param category can be both category/tag
 */
@Parcelize
data class Quiz(
    val id: Int = 0,
    val point: Double = 0.0,
    val question: String = "",
    val image: String = "",
    val category: String = "",
    val explanation: String = "",
    val answers: List<Answer> = emptyList(),
)   : Parcelable {
    val correctAnswerCount = answers.count { it.isCorrect }
    val hasMultiAnswer = correctAnswerCount > 1
}
