package com.earth.testomania.apis.quizapi.domain.model

/**
 * This is use by API to request specific category
 */
enum class QuizCategory(val category: String = "", val tag: String = "") {
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