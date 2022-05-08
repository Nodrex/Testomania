package com.earth.testomania.presentation.dummy

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "home/dummy")
@Composable
fun DummyScreen() {
    Text(text = "hello empty screen")
}