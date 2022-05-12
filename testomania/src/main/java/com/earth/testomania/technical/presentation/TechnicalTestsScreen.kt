package com.earth.testomania.technical.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
    ) {
        val (progress, horizontalPager, navigation) = createRefs()

        //CreateKiwiUI() //TODO tmp

        CreateQuizOverallProgressUI(
            Modifier.constrainAs(progress) {
                top.linkTo(parent.top /*margin = 0.dp*/)
            }
        )

        CreateQuizNavigationButtonUI(
            Modifier.constrainAs(navigation) {
                bottom.linkTo(parent.bottom)
            }
        )

        HorizontalPager(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .constrainAs(horizontalPager) {
                    bottom.linkTo(navigation.top, margin = 1.dp)
                },
            count = techQuizList.size,
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 10.dp)
            ) {
                CreateQuizInfoUI(techQuizList[page])
                CreateQuizUI(techQuizList[page])
                LazyColumn(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    techQuizList[page].possibleAnswers.forEach { possibleAnswer ->
                        item {
                            CreateQuizAnswerUI(possibleAnswer)
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateScreen(listOf(defaultTechQuiz()))
}