package com.earth.testomania.technical.presentation

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    val viewmodel: QuizViewModel = hiltViewModel()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true ) {
        viewmodel.data.collectLatest {
            //scaffoldState.snackbarHostState.showSnackbar("data => ${it.size}")
        }
    }

    CreateScreen(listOf(defaultTechQuiz()))

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CreateScreen(techQuizList: List<TechQuiz>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
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