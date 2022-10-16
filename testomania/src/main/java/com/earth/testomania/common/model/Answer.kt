package com.earth.testomania.common.model

/**
 * @param tag can be A, B, C .. or 1, 2, 3
 */
data class Answer(
    val tag: Char,
    val text: String,
    val image: String,
    val isCorrect: Boolean,
)