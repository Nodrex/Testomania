package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import com.earth.testomania.core.helper.defaultTechQuiz
import com.earth.testomania.technical.domain.model.TechQuiz

@Composable
fun CreateQuizUI(techQuiz: TechQuiz) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_question_mark),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = techQuiz.question)
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
private fun Preview() {
    CreateQuizUI(defaultTechQuiz())
}