package com.earth.testomania.presentation.skillz

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
@Destination(
    route = "home/skillz",
//    style = DestinationStyle.BottomSheet::class
)
@Composable
@Preview
fun SkillzTestScreen() {

    val viewModel: SkillzTestsViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxWidth()) {
        val currentTest = 25
        Text(text = viewModel.state.getOrNull(currentTest)?.question ?: "")
        Spacer(modifier = Modifier.height(15.dp))
        viewModel.state.getOrNull(currentTest)?.possibleAnswers?.forEach {
            Text(text = it.text)
        }
    }
}