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
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.earth.testomania.core.helper.defaultTechQuizWrapper
import com.earth.testomania.destinations.ResultScreenDestination
import com.earth.testomania.presentation.result.ResultData
import com.earth.testomania.technical.domain.model.TechQuizWrapper
import com.earth.testomania.technical.presentation.ui_parts.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.launch

@Destination(
    route = "home/technical_tests",
    deepLinks = [
        DeepLink(uriPattern = "testomania://home/technical_tests")
    ]
)
@Composable
fun TechnicalTestsScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: QuizViewModel = hiltViewModel()

    val data = viewModel.data

    val resultData = ResultData("TechnicalTests", 0.3f, false)
    navigator.navigate(ResultScreenDestination(resultData))

    if (data.isEmpty()) LoadingScreen()
    else CreateQuizScreen(data)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CreateQuizScreen(techQuizList: List<TechQuizWrapper>) {
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

        QuestionAndAnswers(modifier = Modifier.constrainAs(pager) {
            top.linkTo(progressBar.bottom, margin = 10.dp)
            bottom.linkTo(navigation.top)
            height = Dimension.fillToConstraints
        }, techQuizList, pagerState)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .constrainAs(navigation) {
                top.linkTo(pager.bottom)
                bottom.linkTo(parent.bottom)
            }, horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)) {

            ButtonSecondary(onClick = { /*TODO show finish screen*/ }, Modifier.weight(1f)) {
                Text(text = stringResource(R.string.navigation_finish))

            }
            val scope = rememberCoroutineScope()

            ButtonPrimary(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }, Modifier.weight(1f)) {
                Text(text = stringResource(R.string.navigation_next))
                Icon(painter = painterResource(id = R.drawable.ic_orbit_chevron_right),
                    contentDescription = "")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun QuestionAndAnswers(
    modifier: Modifier,
    techQuizList: List<TechQuizWrapper>,
    pagerState: PagerState,
) {
    val answerPadding = 10.dp

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        count = techQuizList.size,
        state = pagerState,
    ) { page ->
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (question, answers, illustration) = createRefs()

            CreateQuizUI(modifier = Modifier
                .constrainAs(question) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth(), techQuizList[page])

            BoxWithConstraints(modifier = Modifier.constrainAs(illustration) {
                top.linkTo(question.bottom)
                bottom.linkTo(answers.top)
                height = Dimension.fillToConstraints
            }) {
                if (maxHeight > 100.dp) {
                    CategoryIllustration(category = techQuizList[page].quiz.category)
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
        }
    }
}

@Preview
@Composable
private fun PreviewComposeUI() {
    CreateQuizScreen(listOf(defaultTechQuizWrapper()))
}