package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.earth.testomania.core.helper.defaultTechQuiz
import com.earth.testomania.core.presentation.custom.AnswerTileState
import com.earth.testomania.core.presentation.custom.TestomaniaChoiceTile
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun CreateQuizAnswerUI(possibleAnswer: Map.Entry<String, String>) {

    var isSelected by remember { mutableStateOf(false) }
    val answerState by remember { mutableStateOf(AnswerTileState.INCORRECT) }

    TestomaniaChoiceTile(
        selected = isSelected,
        onSelect = { isSelected = !isSelected },
        title = possibleAnswer.key,
        toggleColorType = answerState,
        content = {
            Text(
                modifier = Modifier.fillMaxWidth(),
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
