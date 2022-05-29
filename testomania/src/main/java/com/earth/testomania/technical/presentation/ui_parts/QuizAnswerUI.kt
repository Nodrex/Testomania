package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.earth.testomania.core.helper.defaultTechQuiz
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun CreateQuizAnswerUI(possibleAnswer: Map.Entry<String, String>) {

    var selected by remember {
        mutableStateOf(false)
    }

    val possibleAnswerIndexTextColor by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.White
            false -> Color.Black
        }
    )

    var isSelected by remember { mutableStateOf(false) }

    ChoiceTile(
        selected = isSelected,
        onSelect = { isSelected = !isSelected },
        title = {
            Text(
                text = possibleAnswer.key,
                fontWeight = FontWeight.Bold,
                color = possibleAnswerIndexTextColor
            )
        },
        footer = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = possibleAnswer.value,
                fontWeight = FontWeight(499),
            )
        },
    )
}

@Preview
@Composable
private fun Preview() {
    CreateQuizAnswerUI(defaultTechQuiz().possibleAnswers.firstNotNullOf {
        it
    })
}
