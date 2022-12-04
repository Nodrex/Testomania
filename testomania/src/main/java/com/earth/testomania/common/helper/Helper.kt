package com.earth.testomania.common.helper

import com.earth.testomania.common.model.Quiz
import com.earth.testomania.common.model.QuizUIState

fun defaultTechQuizWrapper() = QuizUIState(
    Quiz(
        question = "Which is best os on the earth",
        category = "Tech/OS",
        explanation = """Android is most popular OS on earth cause it's market share is 42.67% (year:2021).
            it even surpass windows, which is 29.56% depend on https://gs.statcounter.com/os-market-share""".trimIndent(),
    )
)

fun EmptyChar() = ' '

fun Any?.println() {
    println(this)
}