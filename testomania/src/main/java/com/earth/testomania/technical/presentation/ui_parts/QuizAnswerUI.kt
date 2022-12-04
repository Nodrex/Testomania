package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.earth.testomania.common.custom_ui_components.AnswerTileState
import com.earth.testomania.common.custom_ui_components.TestomaniaChoiceTile
import com.earth.testomania.common.custom_ui_components.getAnswerTileState
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.technical.presentation.QuizViewModel
import com.earth.testomania.technical.presentation.quizViewModel
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun CreateQuizAnswerUI(
    quizUIState: QuizUIState,
    answer: Answer,
) {
    val viewModel: QuizViewModel = quizViewModel()
    val isSelected = viewModel.wasAlreadyAnswered(quizUIState, answer.tag)
    val enabled = viewModel.enableAnswerSelection(quizUIState)

    val answerState = if (isSelected) {
        getAnswerTileState(
            viewModel.isCorrectAnswer(
                quizUIState,
                answer.tag
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
            viewModel.saveAnswer(quizUIState, answer.tag)
        },
        title = answer.tag.toString(),
        toggleColorType = answerState,
        content = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = answer.text,
                fontWeight = FontWeight(499),
            )
        },
    )
}
