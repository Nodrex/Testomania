package com.earth.testomania.common.model

data class Answer(
    val tag: Char, //can be A, B, C .. or 1, 2, 3
    val value: String,
    val imageQuiz: ImageQuiz,
    val isCorrect: Boolean
)