package com.earth.testomania.quiz_screen.ui_components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.earth.testomania.R
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.destinations.ResultScreenDestination
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.result_screen.domain.use_case.ResultDataCollectorUseCase
import com.earth.testomania.ui.theme.BottomBarColorDark
import com.earth.testomania.ui.theme.BottomBarColorLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.SurfaceCard
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

    val systemUiController = rememberSystemUiController()
    val bottomBarColor = if (isSystemInDarkTheme()) BottomBarColorDark else BottomBarColorLight

    DisposableEffect(systemUiController) {
        systemUiController.setNavigationBarColor(
            color = bottomBarColor,
        )

        onDispose {
            systemUiController.setNavigationBarColor(
                color = Color.Unspecified,
            )
        }
    }

    BottomAppBar(
        modifier =
        Modifier
            .fillMaxWidth()
            .constrainAs(navigation) {
                top.linkTo(pager.bottom)
                bottom.linkTo(parent.bottom)
            },
        containerColor = bottomBarColor,
        actions = {
            Spacer(modifier = Modifier.width(10.dp))
            BottomBarItem(R.string.navigation_finish, R.drawable.finish_line) {
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
            BottomBarItem(R.string.feedback, R.drawable.ic_feedback) {
                scope.launch {
                    modalBottomSheetState.show()
                }
            }
            BottomBarItem(R.string.help, R.drawable.ic_chat_gpt) {
                //TODO open ChatGPT BottomSheet
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
                    //modifier = Modifier.fillMaxSize().padding(10.dp),
                    painter = painterResource(id = R.drawable.ic_orbit_chevron_double_forward),
                    contentDescription = "",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
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
        val name = stringResource(strId)
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            painter = painterResource(drawableId),
            contentDescription = name,
            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
        )
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
