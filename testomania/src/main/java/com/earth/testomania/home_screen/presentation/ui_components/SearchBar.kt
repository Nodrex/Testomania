package com.earth.testomania.home_screen.presentation

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(modifier: Modifier) {
    TextField(
        modifier = modifier,
        value = "sdfsdf", onValueChange = {})
}