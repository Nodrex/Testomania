package com.earth.testomania.quiz_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.common.model.QuizUIState
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun CreateQuizUI(modifier: Modifier = Modifier, quizUIState: QuizUIState) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .padding(top = 10.dp)
    ) {
        val style = AnnotatedString.Range(
            SpanStyle(fontWeight = FontWeight.Bold),
            0,
            quizUIState.quiz.category.length
        )

        val text = AnnotatedString(
            quizUIState.quiz.category + "\n" + quizUIState.quiz.question,
            spanStyles = listOf(style)
        )

        Text(
            text = text,
            fontWeight = FontWeight(499),
            fontSize = 16.sp
        )
    }
}

