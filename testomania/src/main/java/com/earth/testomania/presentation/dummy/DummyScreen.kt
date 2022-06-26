package com.earth.testomania.presentation.dummy

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

const val DUMMY_ROUT = "home/dummy"

@Destination(route = DUMMY_ROUT)
@Composable
fun DummyScreen() {
    Text(text = "hello empty screen")
}