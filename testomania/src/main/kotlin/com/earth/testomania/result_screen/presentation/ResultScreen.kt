package com.earth.testomania.result_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.earth.testomania.common.custom_ui_components.AnswerTileState
import com.earth.testomania.common.custom_ui_components.TestomaniaChoiceTile
import com.earth.testomania.result_screen.domain.model.IncorrectlyAnsweredQuizModel
import com.earth.testomania.result_screen.domain.model.ResultData
import com.ramcosta.composedestinations.annotation.Destination
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.LinearProgressIndicator
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlin.math.roundToInt

@Destination(
    route = "home/result"
)
@Composable
fun ResultScreen(
    resultData: ResultData
) {

    val viewModel: ResultScreenViewModel = hiltViewModel()
    viewModel.resultData = resultData

    val illustrationRes = if (viewModel.resultData.isTestDone) R.drawable.il_orbit_success
    else R.drawable.il_orbit_error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(start = 8.dp, end = 8.dp, top = 3.dp),
    ) {
        MainResultItem(
            testIconRes = viewModel.resultData.testIconRes,
            testName = viewModel.resultData.quizCategoryName,
            mainColor = OrbitTheme.colors.info.normal,
            progress = viewModel.resultData.overallProgress
        )
        LazyColumn(
            modifier = Modifier,
        ) {
            item(key = 0) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 10.dp,
                            top = 10.dp
                        ),
                    painter = painterResource(id = illustrationRes),
                    contentDescription = "",

                    )
            }
            viewModel.resultData.incorrectQuestions.forEach { item ->
                item(key = item.quiz.id + 1) {
                    IncorrectAnsweredQuestion(item = item)
                }
            }
        }
    }

}

@Composable
fun IncorrectAnsweredQuestion(item: IncorrectlyAnsweredQuizModel) {
    SurfaceCard(
        modifier = Modifier
            .padding(0.dp, 6.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(OrbitTheme.colors.surface.main)
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = item.quiz.question,
                fontSize = 20.sp
            )

            val incorrectAnswer = item.incorrectAnswersList.firstOrNull()
            TestomaniaChoiceTile(
                selected = false,
                enabled = false,
                onSelect = {},
                title = incorrectAnswer?.tag.toString(),
                toggleColorType = AnswerTileState.INCORRECT,
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = incorrectAnswer?.text ?: "",
                        fontWeight = FontWeight(499),
                    )
                },
                indicateWithoutSelection = true
            )

            val correctAnswer = item.correctAnswersList.firstOrNull()
            TestomaniaChoiceTile(
                selected = false,
                enabled = false,
                onSelect = {},
                title = correctAnswer?.tag.toString(),
                toggleColorType = AnswerTileState.CORRECT,
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = correctAnswer?.text ?: "",
                        fontWeight = FontWeight(499),
                    )
                },
                indicateWithoutSelection = true
            )
        }
    }
}

@Composable
fun MainResultItem(
    testIconRes: Int,
    testName: String = "",
    mainColor: Color = OrbitTheme.colors.info.normal,
    progress: Double = 0.0
) {

    val lighterColor = mainColor.copy(alpha = 0.1f)
    val lightColor = mainColor.copy(alpha = 0.2f)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
            .fillMaxWidth()
            .background(lighterColor)
            .padding(8.dp, 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(lightColor)
                .padding(10.dp),
            painter = painterResource(id = testIconRes),
            contentDescription = "",
            colorFilter = ColorFilter.tint(mainColor)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp, 0.dp)
        ) {
            Text(
                text = testName, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(
                color = mainColor,
                fontSize = 12.sp,
                text = "${(progress * 10000).roundToInt() / 100}% correct"
            )
            LinearProgressIndicator(
                progress = progress.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp)
                    .height(8.dp),
            )
        }
    }
}