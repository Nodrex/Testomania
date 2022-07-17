package com.earth.testomania.presentation.dummy

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

const val DUMMY_ROUTE = "home/dummy"

@Destination(route = DUMMY_ROUTE)
@Composable
fun DummyScreen() {
    Text(text = "hello empty screen")
}