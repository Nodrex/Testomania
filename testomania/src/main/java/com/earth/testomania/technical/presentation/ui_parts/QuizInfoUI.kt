package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreateQuizInfoUI() {
    Column() {
        Text(text = "Category")
    }
}

@Preview
@Composable
private fun Preview() {
    CreateQuizInfoUI()
}