package com.earth.testomania.technical.presentation.ui_parts

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

@Composable
fun CreateMultiAnswerQuizFinishButton(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
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
    CreateMultiAnswerQuizFinishButton(Modifier)
}