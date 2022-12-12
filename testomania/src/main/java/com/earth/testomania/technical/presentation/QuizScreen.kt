@file:OptIn(ExperimentalPagerApi::class)

package com.earth.testomania.technical.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.res.ResourcesCompat.ID_NULL
import com.earth.testomania.R
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.destinations.ResultScreenDestination
import com.earth.testomania.home_screen.presentation.destinations.DestinationViewModel
import com.earth.testomania.result_screen.domain.use_case.ResultDataCollectorUseCase
import com.earth.testomania.technical.presentation.ui_parts.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.launch


const val TECHNICAL_ROUTE = "home/technical_tests"

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
    techQuizList: List<QuizUIState>,
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
            .padding(start = 10.dp, end = 10.dp), currentProgress, techQuizList.size)

        QuestionAndAnswers(
            modifier = Modifier.constrainAs(pager) {
                top.linkTo(progressBar.bottom, margin = 10.dp)
                bottom.linkTo(navigation.top)
                height = Dimension.fillToConstraints
            },
            techQuizList,
            pagerState,
            viewModel
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .constrainAs(navigation) {
                    top.linkTo(pager.bottom)
                    bottom.linkTo(parent.bottom)
                }, horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)
        ) {
            // TODO
//            val categoryName = if (viewModel.category == QuizCategory.ALL) {
//                stringResource(id = R.string.technical_tests)
//            } else viewModel.getCategoryName()

            ButtonSecondary(
                onClick = {
                    navigator.navigateUp()
                    navigator.navigate(
                        ResultScreenDestination(
                            ResultDataCollectorUseCase().getTechnicalTestResult(
                                techQuizList,
                                viewModel.overallScore,
                                "Quiz" // TODO
                            )
                        )
                    )
                },
                Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.navigation_finish))

            }
            val scope = rememberCoroutineScope()

            ButtonPrimary(onClick = {
                scope.launch {
                    if (pagerState.pageCount == pagerState.currentPage + 1) {
                        val unansweredQuestion =
                            findFirstIndexOfUnansweredQuestion(techQuizList, pagerState)

                        pagerState.animateScrollToPage(unansweredQuestion)

                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }, Modifier.weight(1f)) {
                Text(text = stringResource(R.string.navigation_next))
                Icon(
                    painter = painterResource(id = R.drawable.ic_orbit_chevron_right),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun QuestionAndAnswers(
    modifier: Modifier,
    techQuizList: List<QuizUIState>,
    pagerState: PagerState,
    viewModel: DestinationViewModel,
) {
    val answerPadding = 10.dp

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        count = techQuizList.size,
        state = pagerState,
    ) { pageIndex ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (question, answers, illustration) = createRefs()

            CreateQuizUI(modifier = Modifier
                .constrainAs(question) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth(), techQuizList[pageIndex])

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
                    CategoryIllustration(category = techQuizList[pageIndex].quiz.category)
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
                techQuizList[pageIndex].apply {
                    quiz.answers.forEach { possibleAnswer ->
                        item(key = possibleAnswer.tag) {
                            CreateQuizAnswerUI(
                                techQuizList[pageIndex],
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

private fun findFirstIndexOfUnansweredQuestion(
    techQuizList: List<QuizUIState>,
    pagerState: PagerState,
): Int {
    return techQuizList.indexOfFirst {
        it.selectedAnswers.isEmpty()
    }.takeIf { it >= 0 } ?: pagerState.currentPage
}

@Preview
@Composable
private fun PreviewComposeUI() {
    //CreateQuizScreen(listOf(defaultTechQuizWrapper()))
}
