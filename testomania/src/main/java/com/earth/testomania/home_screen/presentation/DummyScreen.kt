package com.earth.testomania.home_screen.presentation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import kiwi.orbit.compose.ui.controls.Text

const val DUMMY_ROUTE = "home/dummy"

@Destination(route = DUMMY_ROUTE)
@Composable
fun DummyScreen() {
    Text(text = "hello empty screen")
}