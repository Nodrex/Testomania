package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R

@Composable
fun CreateQuizNavigationButtonUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Button(onClick = { }) {
            Text(text = "Prev")
        }
        Button(onClick = { }) {
            Text(text = "Done")
        }
        Button(onClick = { }) {
            Text(text = "Next")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CreateQuizNavigationButtonUI()
}