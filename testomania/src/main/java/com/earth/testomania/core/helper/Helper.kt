package com.earth.testomania.core.helper

import com.earth.testomania.technical.domain.model.TechQuiz

fun defaultTechQuiz() = TechQuiz(
    question = "Which is best os on the earth",
    category = "Tech/OS",
    explanation = "Android is most popular OS on earth cause it's market share is 42.67% (year:2021).\nit even surpass windows, which is 29.56% depend on https://gs.statcounter.com/os-market-share",
    possibleAnswers = mapOf(
        "A" to "Android",
        "B" to "Windows",
        "C" to "Ios",
        "D" to "Linux",
    ),
    correctAnswers = mapOf(
        "A" to true,
        "B" to false,
        "C" to false,
        "D" to false,
    )
)