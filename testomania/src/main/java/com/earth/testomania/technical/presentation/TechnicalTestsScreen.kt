package com.earth.testomania.technical.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.core.helper.defaultTechQuiz
import com.earth.testomania.technical.domain.model.TechQuiz
import com.earth.testomania.technical.presentation.ui_parts.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalAnimationApi::class)
@Destination(
    route = "home/technical_tests"
)
@Composable
fun TechnicalTestsScreen() {
    val viewModel: QuizViewModel = hiltViewModel()

    var data by remember {
        mutableStateOf<List<TechQuiz>>(emptyList())
    }

    LaunchedEffect(key1 = true) {
        viewModel.data.collectLatest {
            data = it
        }
    }

    CreateScreen(data)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CreateScreen(techQuizList: List<TechQuiz>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {

        CreateKiwiUI() //TODO tmp

        CreateQuizOverallProgressUI()

        HorizontalPager(
            modifier = Modifier.wrapContentSize(),
            count = techQuizList.size
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp)
            ) {
                CreateQuizInfoUI(techQuizList[page])
                CreateQuizUI(techQuizList[page])

                LazyColumn {
                    techQuizList[page].possibleAnswers.forEach { possibleAnswer ->
                        item {
                            CreateQuizAnswerUI(possibleAnswer)
                        }
                    }
                }

                //CreateQuizExplanationUI()
            }
        }

        CreateQuizNavigationButtonUI()
    }
}

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateScreen(listOf(defaultTechQuiz()))
}