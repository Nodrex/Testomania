package com.earth.testomania.technical.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.technical.presentation.ui_parts.CreateQuizInfoUI
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalAnimationApi::class)
@Destination(
    route = "home/technical_tests"
)
@Composable
@Preview
fun TechnicalTestsScreen() {
    val viewmodel: QuizViewModel = hiltViewModel()

    CreateQuizInfoUI()
}