package com.earth.testomania.apis.quiz.quizapi.domain.model

import com.earth.testomania.R

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
    JavaScript("", "javascript"),
    Kubernetes("", "kubernetes"),
    WordPress("", "wordpress"),
}