package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.core.helper.defaultTechQuizWrapper
import com.earth.testomania.technical.domain.model.TechQuizWrapper
import com.earth.testomania.technical.presentation.QuizViewModel

@Composable
fun CreateMultiAnswerQuizFinishButton(modifier: Modifier, techQuizWrapper: TechQuizWrapper) {

    val viewModel: QuizViewModel = hiltViewModel()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .clickable {
                viewModel.multiSelectionWasDone(techQuizWrapper)
            },
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { }) {
            Text(text = "Done")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CreateMultiAnswerQuizFinishButton(Modifier, defaultTechQuizWrapper())
}