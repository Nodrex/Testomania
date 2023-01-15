package com.earth.testomania.common.data

data class Developer(
    val fullName: String,
    val githubProfileLink: String,
    val linkedinProfileLink: String
)

val developers = listOf(
    Developer(
        "Giorgi Shalvashvili",
        "https://github.com/shalva97",
        "https://www.linkedin.com/in/giorgi-shalvashvili/"
    ),
    Developer(
        "Kartlos Diakonidze",
        "https://github.com/kdiakonidze",
        "https://www.linkedin.com/in/kartlos-diakonidze-93bb91138/"
    ),
    Developer(
        "Nodar Tchumbadze",
        "https://github.com/Nodrex",
        "https://www.linkedin.com/in/nodar-tchumbadze-15351163/"
    ),
    Developer(
        "Nika Mgaloblishvili",
        "https://github.com/nmgalo",
        "https://www.linkedin.com/in/nika-mgaloblishvili/"
    ),
    Developer(
        "Lika Glonti",
        "https://github.com/likaGlonti",
        "https://www.linkedin.com/in/lika-glonti-p120/"
    )
)