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

@Composable
fun CreateQuizExplanationUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_explanation),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Some information to explain")
    }
}

@Preview
@Composable
private fun Preview() {
    CreateQuizExplanationUI()
}