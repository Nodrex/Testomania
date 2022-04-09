package com.earth.testomania

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalUnitApi::class)
@Preview
@Composable
fun TopBar() {
    Surface(color = MaterialTheme.colors.primary) {
        Box(
            modifier = Modifier
                .height(66.dp)
                .padding(start = 22.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = "T e s t o m a n i a",
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(24f, TextUnitType.Sp),
                textAlign = TextAlign.Center
            )
        }
    }
}