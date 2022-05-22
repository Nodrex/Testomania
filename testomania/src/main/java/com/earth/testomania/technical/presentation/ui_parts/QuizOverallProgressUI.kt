package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.ui.theme.LightGray
import com.earth.testomania.ui.theme.Orange

@Composable
fun CreateQuizOverallProgressUI(modifier: Modifier, currentProgress: Int, maxProgress: Int) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .matchParentSize()
                .height(15.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    border = BorderStroke(1.5.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp)
                ),
            backgroundColor = LightGray,
            color = Orange,
            progress = if(currentProgress == 0) 0f else (currentProgress.toFloat()/maxProgress.toFloat())
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            text = "$currentProgress/$maxProgress",
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Preview
@Composable
private fun Preview() {
    CreateQuizOverallProgressUI(Modifier, 7, 10)
}