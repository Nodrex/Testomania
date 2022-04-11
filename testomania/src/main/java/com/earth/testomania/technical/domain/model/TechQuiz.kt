package com.earth.testomania.technical.domain.model

data class TechQuiz(
    val id: Int = 0,
    val question: String = "",
    val category: String = "", //should be both category/tag
    val explanation: String = "",
    val hasMultiAnswer: Boolean = false,
    val possibleAnswers: Map<String, String> = emptyMap(),
    val correctAnswers: List<String> = emptyList(),
)

class ANSWER {
    companion object {
        val A = "answer_a"
        val B = "answer_b"
        val C = "answer_c"
        val D = "answer_d"
        val E = "answer_e"
        val F = "answer_f"
    }
}

val answerKeyList = listOf(
    ANSWER.A,
    ANSWER.B,
    ANSWER.C,
    ANSWER.D,
    ANSWER.E,
    ANSWER.F,
)
