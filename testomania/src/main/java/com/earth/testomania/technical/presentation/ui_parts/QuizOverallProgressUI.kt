package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.ui.theme.Gray
import com.earth.testomania.ui.theme.LightDark
import com.earth.testomania.ui.theme.LightGray
import com.earth.testomania.ui.theme.Orange

@Composable
fun CreateQuizOverallProgressUI() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    border = BorderStroke(1.5.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp)
                ),
            backgroundColor = LightGray,
            color = Orange,
            progress = 0.1f
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 3.dp),
                color = Gray,
                text = "Progress"
            )
            Text(
                modifier = Modifier.padding(end = 3.dp),
                color = LightDark,
                text = "5/10"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview
@Composable
private fun Preview() {
    CreateQuizOverallProgressUI()
}