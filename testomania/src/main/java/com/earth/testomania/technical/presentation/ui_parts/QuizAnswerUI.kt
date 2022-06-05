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
fun CreateQuizAnswerUI(
    techQuizWrapper: TechQuizWrapper,
    possibleAnswer: Map.Entry<String, String>,
) {
    val viewModel: QuizViewModel = hiltViewModel()
    val answerState by remember { mutableStateOf(AnswerTileState.INCORRECT) }

    val isSelected = viewModel.wasSelected(techQuizWrapper, possibleAnswer.key)

    //val enabled = viewModel.enableAnswerSelection(techQuizWrapper)

    TestomaniaChoiceTile(
        selected = isSelected,
        onSelect = {
            //enabled = enabled,
            viewModel.saveAnswer(techQuizWrapper, possibleAnswer.key)
                   },
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
    CreateQuizAnswerUI(
        defaultTechQuizWrapper(),
        defaultTechQuizWrapper().quiz.possibleAnswers.firstNotNullOf {
            it
        }
    )
}
