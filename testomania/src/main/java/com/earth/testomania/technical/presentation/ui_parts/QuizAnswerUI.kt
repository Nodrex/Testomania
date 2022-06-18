package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.core.helper.defaultTechQuizWrapper
import com.earth.testomania.core.presentation.custom.AnswerTileState
import com.earth.testomania.core.presentation.custom.TestomaniaChoiceTile
import com.earth.testomania.core.presentation.custom.getAnswerTileState
import com.earth.testomania.technical.domain.model.TechQuizWrapper
import com.earth.testomania.technical.presentation.QuizViewModel
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun CreateQuizAnswerUI(
    techQuizWrapper: TechQuizWrapper,
    possibleAnswer: Map.Entry<String, String>,
) {
    val viewModel: QuizViewModel = hiltViewModel()
    val isSelected = viewModel.wasAlreadyAnswered(techQuizWrapper, possibleAnswer.key)
    val enabled = viewModel.enableAnswerSelection(techQuizWrapper)

    val answerState = if (isSelected) {
        getAnswerTileState(
            viewModel.isCorrectAnswer(
                techQuizWrapper,
                possibleAnswer.key
            )
        )
    } else AnswerTileState.NEUTRAL

    TestomaniaChoiceTile(
        //TODO replace with KIWI style
        /*modifier = Modifier.background(
            if (enabled) Color.Transparent else Color.LightGray
        ),*/
        selected = isSelected,
        enabled = enabled,
        onSelect = {
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
