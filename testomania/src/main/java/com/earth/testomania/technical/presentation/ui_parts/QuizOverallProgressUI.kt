package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.ui.controls.LinearProgressIndicator
import kiwi.orbit.compose.ui.controls.Text

@Preview
@Composable
fun OverallProgress(currentProgress: Int = 2, maxProgress: Int = 4) {
    Box {
        LinearProgressIndicator(
            progress = (currentProgress.toFloat() / maxProgress.toFloat()),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(15.dp))

        if (maxProgress != 0) {
            Text(text = "$currentProgress/$maxProgress",
                modifier = Modifier.align(Alignment.Center), fontWeight = FontWeight.Bold)
        }
    }
}
