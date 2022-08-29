package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.common.helper.defaultTechQuizWrapper
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper
import com.earth.testomania.technical.presentation.QuizViewModel
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun CreateMultiAnswerQuizFinishButton(
    modifier: Modifier,
    techQuizItemWrapper: TechQuizItemWrapper
) {

    val viewModel: QuizViewModel = hiltViewModel()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            viewModel.multiSelectionWasDone(techQuizItemWrapper)
        }) {
            Text(text = "Done")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CreateMultiAnswerQuizFinishButton(Modifier, defaultTechQuizWrapper())
}