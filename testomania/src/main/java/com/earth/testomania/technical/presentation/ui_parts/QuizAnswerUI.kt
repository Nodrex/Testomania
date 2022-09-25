package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.common.custom_ui_components.AnswerTileState
import com.earth.testomania.common.custom_ui_components.TestomaniaChoiceTile
import com.earth.testomania.common.custom_ui_components.getAnswerTileState
import com.earth.testomania.common.helper.defaultTechQuizWrapper
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper
import com.earth.testomania.technical.presentation.QuizViewModel
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun CreateQuizAnswerUI(
    techQuizItemWrapper: TechQuizItemWrapper,
    possibleAnswer: Map.Entry<String, String>,
) {
    val viewModel: QuizViewModel = hiltViewModel()
    val isSelected = viewModel.wasAlreadyAnswered(techQuizItemWrapper, possibleAnswer.key)
    val enabled = viewModel.enableAnswerSelection(techQuizItemWrapper)

    val answerState = if (isSelected) {
        getAnswerTileState(
            viewModel.isCorrectAnswer(
                techQuizItemWrapper,
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
            viewModel.saveAnswer(techQuizItemWrapper, possibleAnswer.key)
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
