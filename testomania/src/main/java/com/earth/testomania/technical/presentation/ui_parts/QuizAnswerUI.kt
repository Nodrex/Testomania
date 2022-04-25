package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CreateQuizAnswerUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .border(
                border = BorderStroke(1.5.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(all = 12.dp),
    ) {
        Box(
            Modifier
                .border(
                    border = BorderStroke(1.5.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
        ) {
            Text(
                text = "A"
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        //TODO not suited well fro multi line answer, need other ui for that
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp),
                text = "Some possible answer"
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Preview
@Composable
private fun Preview() {
    CreateQuizAnswerUI()
}
