package com.earth.testomania.apis.quiz.opentdb.data.source.remote.dto

data class ResultDto(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)