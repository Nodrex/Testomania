package com.earth.testomania.technical.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.core.helper.defaultTechQuizWrapper
import com.earth.testomania.core.log
import com.earth.testomania.technical.domain.model.TechQuizWrapper
import com.earth.testomania.technical.presentation.ui_parts.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    route = "home/technical_tests",
    deepLinks = [
        DeepLink(uriPattern = "testomania://home/technical_tests")
    ]
)
@Composable
fun TechnicalTestsScreen() {
    val viewModel: QuizViewModel = hiltViewModel()

    val data = viewModel.data

    if (data.isNotEmpty()) CreateScreen(data)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CreateScreen(techQuizList: List<TechQuizWrapper>) {
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
            .padding(all = 10.dp),
    ) {
        val (progressBar, pager) = createRefs()

        OverallProgress(modifier = Modifier.constrainAs(progressBar) {
            top.linkTo(parent.top)
        }, currentProgress, techQuizList.size)

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(pager) {
                    top.linkTo(progressBar.bottom, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                },
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
                    techQuizList[page].quiz.apply {
                        possibleAnswers.forEach { possibleAnswer ->
                            item(key = possibleAnswer.key) {
                                CreateQuizAnswerUI(
                                    techQuizList[page],
                                    possibleAnswer,
                                )
                            }
                        }
                    }
                }
                if(techQuizList[page].quiz.hasMultiAnswer){
                    CreateMultiAnswerQuizFinishButton(Modifier, techQuizList[page])
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateScreen(listOf(defaultTechQuizWrapper()))
}