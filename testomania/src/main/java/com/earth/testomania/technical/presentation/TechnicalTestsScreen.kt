package com.earth.testomania.technical.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.core.helper.defaultTechQuiz
import com.earth.testomania.technical.domain.model.TechQuiz
import com.earth.testomania.technical.presentation.ui_parts.CreateQuizAnswerUI
import com.earth.testomania.technical.presentation.ui_parts.CreateQuizUI
import com.earth.testomania.technical.presentation.ui_parts.OverallProgress
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@Destination(
    route = "home/technical_tests",
    deepLinks = [
        DeepLink(uriPattern = "testomania://home/technical_tests")
    ]
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

    val pagerState = rememberPagerState()

    var currentProgress by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentProgress = page + 1
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val (progressBar, pager) = createRefs()

        OverallProgress(modifier = Modifier.constrainAs(progressBar) {
            top.linkTo(parent.top)
        }, currentProgress, techQuizList.size)

        QuestionAndAnswers(modifier = Modifier.constrainAs(pager) {
            top.linkTo(progressBar.bottom, margin = 10.dp)
            bottom.linkTo(parent.bottom)
        }, techQuizList, pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun QuestionAndAnswers(
    modifier: Modifier,
    techQuizList: List<TechQuiz>,
    pagerState: PagerState,
) {
    HorizontalPager(
        modifier = modifier
            .fillMaxSize(),
        count = techQuizList.size,
        state = pagerState,
    ) { page ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (question, answers) = createRefs()

            CreateQuizUI(modifier = Modifier
                .constrainAs(question) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth(), techQuizList[page])

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(answers) {
                        top.linkTo(question.bottom)
                        bottom.linkTo(parent.bottom)

                        linkTo(question.bottom, parent.bottom, bias = 1f)
                    }
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

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateScreen(listOf(defaultTechQuiz()))
}