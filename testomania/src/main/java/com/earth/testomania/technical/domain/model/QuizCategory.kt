package com.earth.testomania.technical.domain.model

enum class QuizCategory(val category: String = "", val tag: String = "") {
    ALL(),
    Linux("Linux"),
    DevOps("DevOps"),
    Docker("Docker"),
    Programing("Code"),
    SQL("SQL"),
    PHP("", "PHP"),
    HTML("", "HTML"),
    MySql("", "MySql"),
}