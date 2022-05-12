package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import com.earth.testomania.core.helper.defaultTechQuiz
import kotlin.random.Random

@Composable
fun CreateQuizAnswerUI(possibleAnswer: Map.Entry<String, String>) {

    var selected by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.Green
            false -> Color.Black
        }
    )

    val possibleAnswerIndicatorBackColor by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.Green
            false -> Color.Transparent
        }
    )

    val possibleAnswerIndicatorTextColor by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.White
            false -> Color.Black
        }
    )

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.5.dp, color),
                )
                .clickable(
                    onClick = {
                        selected = !selected
                    }
                )
                .padding(all = 12.dp)
        ) {


            Box(
                Modifier
                    .border(
                        border = BorderStroke(1.5.dp, Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(top = 4.dp)
                    .background(
                        color = possibleAnswerIndicatorBackColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(start = 12.dp, end = 12.dp, top = 2.dp, bottom = 6.dp)
            ) {
                Text(
                    text = possibleAnswer.key,
                    fontWeight = FontWeight.Bold,
                    color = possibleAnswerIndicatorTextColor
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            //TODO not suited well fro multi line answer, need refactoring for that
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = possibleAnswer.value,
                fontWeight = FontWeight(499)
            )

            /*
            Icon(
                modifier = Modifier.align(Alignment.Top),
                painter = painterResource(id = tmp() ),
                contentDescription = null,
                tint = Color.Unspecified
            )*/
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

private fun tmp() =
    if (Random(System.currentTimeMillis()).nextBoolean()) R.drawable.ic_correct else R.drawable.ic_wrong

@Preview
@Composable
private fun Preview() {
    CreateQuizAnswerUI(defaultTechQuiz().possibleAnswers.firstNotNullOf {
        it
    })
}
