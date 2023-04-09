package com.earth.testomania.quiz_screen.ui_components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.earth.testomania.R
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.destinations.ResultScreenDestination
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.result_screen.domain.use_case.ResultDataCollectorUseCase
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun ConstraintLayoutScope.BottomBar(
    navigation: ConstrainedLayoutReference,
    pager: ConstrainedLayoutReference,
    pagerState: PagerState,
    quizList: List<QuizUIState>,
    navigator: DestinationsNavigator,
    viewModel: DestinationViewModel,
    modalBottomSheetState: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    BottomAppBar(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 6.dp)
            .constrainAs(navigation) {
                top.linkTo(pager.bottom)
                bottom.linkTo(parent.bottom)
            },
        containerColor = Color.Transparent,
        actions = {
            BottomBarItem(R.string.navigation_finish, R.drawable.ic_check) {
                navigator.navigateUp()
                navigator.navigate(
                    ResultScreenDestination(
                        ResultDataCollectorUseCase().getQuizResult(
                            quizList,
                            viewModel.overallScore,
                            //TODO not good we need to get actually from ViewModel as a category
                            // and not from quiz himself (occurs bug in case of Information
                            // technologies when multiple categories are together),
                            // but for now let's leave
                            // TODO reduce indentation
                            quizList.firstOrNull()?.quiz?.category ?: "Quiz"
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            BottomBarItem(R.string.feedback, R.drawable.ic_feedback) {
                scope.launch {
                    modalBottomSheetState.show()
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            BottomBarItem(R.string.help, R.drawable.ic_help) {
                //TODO open feedback screen
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        if (pagerState.pageCount == pagerState.currentPage + 1) {
                            val unansweredQuestion =
                                findFirstIndexOfUnansweredQuestion(quizList, pagerState)

                            pagerState.animateScrollToPage(unansweredQuestion)

                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_orbit_chevron_double_forward),
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
private fun BottomBarItem(
    @StringRes strId: Int,
    @DrawableRes drawableId: Int,
    onClick: () -> Unit
) {
    //TODO need to detect nav bar height
    SurfaceCard(
        modifier = Modifier
            .size(height = 60.dp, width = 65.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        enabled = true,
        onClick = {
            onClick()
        }) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val name = stringResource(strId)
            Icon(
                painter = painterResource(drawableId),
                contentDescription = name,
                tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun findFirstIndexOfUnansweredQuestion(
    quizList: List<QuizUIState>,
    pagerState: PagerState,
): Int {
    return quizList.indexOfFirst {
        it.selectedAnswers.isEmpty()
    }.takeIf { it >= 0 } ?: pagerState.currentPage
}
