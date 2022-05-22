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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.earth.testomania.R
import com.earth.testomania.core.helper.defaultTechQuiz


@Composable
fun CreateQuizAnswerUI(isCorrect: Boolean, possibleAnswer: Map.Entry<String, String>) {

    var selected by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.Green
            false -> Color.Black
        }
    )

    val possibleAnswerIndexBackColor by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.Green
            false -> Color.Transparent
        }
    )

    val possibleAnswerIndexTextColor by animateColorAsState(
        targetValue = when (selected) {
            true -> Color.White
            false -> Color.Black
        }
    )

    var textOverflow by remember { mutableStateOf(2) }

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
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {

                val (index, question, indicator) = createRefs()

                Box(
                    Modifier
                        .border(
                            border = BorderStroke(1.5.dp, Color.Black),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(top = 4.dp)
                        .background(
                            color = possibleAnswerIndexBackColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(start = 12.dp, end = 12.dp, top = 2.dp, bottom = 6.dp)
                        .constrainAs(index) {
                            start.linkTo(parent.start)
                        }
                ) {
                    Text(
                        text = possibleAnswer.key,
                        fontWeight = FontWeight.Bold,
                        color = possibleAnswerIndexTextColor
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(question) {
                            start.linkTo(index.end, margin = 8.dp)
                            if (selected) end.linkTo(indicator.start, margin = 6.dp)
                            else end.linkTo(parent.end, margin = 6.dp)
                            if (textOverflow == 1) centerVerticallyTo(parent)
                            width = Dimension.fillToConstraints
                        },
                    text = possibleAnswer.value,
                    fontWeight = FontWeight(499),
                    onTextLayout = { textLayoutResult ->
                        textOverflow = textLayoutResult.lineCount
                    },
                )

                if (selected) {
                    val infoIcon = if (isCorrect) R.drawable.ic_correct
                    else {
                        findCorrectAnswer()
                        R.drawable.ic_wrong
                    }

                    Icon(
                        modifier = Modifier.constrainAs(indicator) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                        painter = painterResource(id = infoIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

fun findCorrectAnswer() {
    //TODO
}

@Preview
@Composable
private fun Preview() {
    CreateQuizAnswerUI(true,
        defaultTechQuiz().possibleAnswers.firstNotNullOf {
            it
        })
}
