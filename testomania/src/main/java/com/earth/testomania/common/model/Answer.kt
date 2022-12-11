package com.earth.testomania.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @param tag can be A, B, C .. or 1, 2, 3
 */
@Parcelize
data class Answer(
    val tag: Char,
    val text: String,
    val image: String?,
    val isCorrect: Boolean,
) : Parcelable