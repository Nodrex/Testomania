package com.earth.testomania.apis.quiz.quizapi.domain.model

/**
 * This is use by API to request specific category
 */
enum class QuizApiCategory(val category: String = "", val tag: String = "") {
    ALL,
    Linux("Linux"),
    DevOps("DevOps"),
    Docker("Docker"),
    Programing("Code"),
    SQL("SQL"),
    PHP("", "PHP"),
    HTML("", "HTML"),
    MySql("", "MySql"),
}