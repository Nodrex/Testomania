package com.earth.testomania.technical.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.technical.presentation.ui_parts.*
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalAnimationApi::class)
@Destination(
    route = "home/technical_tests"
)
@Composable
fun TechnicalTestsScreen() {
    val viewmodel: QuizViewModel = hiltViewModel()

    CreateScreen()
}

@Composable
private fun CreateScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        CreateQuizOverallProgressUI()
        CreateQuizInfoUI()
        CreateQuizUI()
        for (i in 0..4) {
            CreateQuizAnswerUI()
        }
        //CreateQuizExplanationUI()
        CreateQuizNavigationButtonUI()
    }
}

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateScreen()
}