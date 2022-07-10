package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.core.helper.defaultTechQuizWrapper
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper

@Composable
fun CreateQuizUI(modifier: Modifier = Modifier, techQuiz: TechQuizItemWrapper) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .padding(top = 10.dp)
    ) {
        val style = AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold),
            0,
            techQuiz.quiz.category.length)

        val text = AnnotatedString(techQuiz.quiz.category + "\n" + techQuiz.quiz.question,
            spanStyles = listOf(style))

        Text(
            text = text,
            fontWeight = FontWeight(499),
            fontSize = 16.sp
        )
    }
}


@Preview
@Composable
private fun Preview() {
    CreateQuizUI(
        techQuiz = defaultTechQuizWrapper()
    )
}