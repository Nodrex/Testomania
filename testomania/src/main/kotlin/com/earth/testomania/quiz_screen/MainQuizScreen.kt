@file:OptIn(ExperimentalPagerApi::class)

package com.earth.testomania.quiz_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.res.ResourcesCompat.ID_NULL
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.quiz_screen.*
import com.earth.testomania.quiz_screen.ui_components.BottomBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


const val TECHNICAL_ROUTE = "home/technical_tests"

//TODO we need to fix it
@Destination(
    route = TECHNICAL_ROUTE,
//    deepLinks = [
//        DeepLink(uriPattern = "testomania://home/technical_tests")
//    ]
)
@Composable
fun MainQuizScreen(
    navigator: DestinationsNavigator,
    viewModel: DestinationViewModel,
) {

    val data = viewModel.data
    val errorState by viewModel.error.collectAsState(initial = ID_NULL)

    if (errorState != ID_NULL) ErrorScreen(errorMessage = errorState)
    else if (data.isEmpty()) LoadingScreen()
    else CreateQuizScreen(data, navigator, viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CreateQuizScreen(
    quizList: List<QuizUIState>,
    navigator: DestinationsNavigator,
    viewModel: DestinationViewModel,
) {
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
        val (progressBar, pager, navigation) = createRefs()

        OverallProgress(modifier = Modifier
            .constrainAs(progressBar) {
                top.linkTo(parent.top)
            }
            .padding(start = 10.dp, end = 10.dp), currentProgress, quizList.size)

        QuestionAndAnswers(
            modifier = Modifier.constrainAs(pager) {
                top.linkTo(progressBar.bottom, margin = 10.dp)
                bottom.linkTo(navigation.top)
                height = Dimension.fillToConstraints
            },
            quizList,
            pagerState,
            viewModel
        )

        BottomBar(navigation, pager, pagerState, quizList, navigator, viewModel)
    }
}

@Composable
private fun QuestionAndAnswers(
    modifier: Modifier,
    quizList: List<QuizUIState>,
    pagerState: PagerState,
    viewModel: DestinationViewModel,
) {
    val answerPadding = 10.dp

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        count = quizList.size,
        state = pagerState,
    ) { pageIndex ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (question, answers, illustration) = createRefs()

            CreateQuizUI(modifier = Modifier
                .constrainAs(question) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth(), quizList[pageIndex])

            BoxWithConstraints(
                modifier = Modifier
                    .constrainAs(illustration) {
                        top.linkTo(question.bottom)
                        bottom.linkTo(answers.top)
                        height = Dimension.fillToConstraints
                    },
                contentAlignment = Alignment.Center
            ) {
                if (maxHeight > 100.dp) {
                    CategoryIllustration(category = quizList[pageIndex].quiz.category)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(answers) {
                        top.linkTo(question.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.preferredWrapContent
                        linkTo(question.bottom, parent.bottom, bias = 1f)
                    },
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(all = answerPadding),
            ) {
                quizList[pageIndex].apply {
                    quiz.answers.forEach { possibleAnswer ->
                        item(key = possibleAnswer.tag) {
                            CreateQuizAnswerUI(
                                quizList[pageIndex],
                                possibleAnswer,
                                viewModel,
                            )
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
    //CreateQuizScreen(listOf(defaultTechQuizWrapper()))
}
