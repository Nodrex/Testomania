package com.earth.testomania.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.earth.testomania.core.presentation.custom.AnswerTileState
import com.earth.testomania.core.presentation.custom.TestomaniaChoiceTile
import com.earth.testomania.ui.theme.IncorrectBkg
import com.earth.testomania.ui.theme.IncorrectBkgDark
import com.earth.testomania.ui.theme.Purple200
import com.earth.testomania.ui.theme.TestomaniaTheme
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.LinearProgressIndicator
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.foundation.darkColors
import kiwi.orbit.compose.ui.foundation.lightColors
import kotlin.math.roundToInt

@Preview(showSystemUi = true)
@Destination(
    route = "home/result"
)
@Composable
fun ResultScreen(
    resultData: ResultData
) {

    val viewModel: ResultScreenViewModel = hiltViewModel()
    viewModel.resultData = resultData

    val illustrationRes =
        if (viewModel.resultData.isTestDone) R.drawable.il_orbit_success else R.drawable.il_orbit_error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        MainResultItem(
            viewModel.resultData.testName,
            mainColor = OrbitTheme.colors.info.normal,
            progress = viewModel.resultData.overallProgress
        )
        Image(
            painter = painterResource(id = illustrationRes),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
        LazyColumn(
            modifier = Modifier,
        ) {
            viewModel.resultData.incorrectQuestions.forEach { item ->
                item(key = item.id) {
                    IncorrectAnsweredQuestion(item = item)
                }
            }
        }
    }

}

@Preview
@Composable
fun PrevIncorrect() {
    val viewModel: ResultScreenViewModel = hiltViewModel()
    viewModel.incorrectQuestions.firstOrNull()?.let {
        IncorrectAnsweredQuestion(it)
    }
}

@Composable
fun IncorrectAnsweredQuestion(item: IncorrectAnsweredQuestionModel) {
    Card(
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
                text = "Question: ${item.questionText}",
                fontSize = 20.sp,
                color = MaterialTheme.colors.onBackground
            )
            TestomaniaChoiceTile(
                selected = false,
                enabled = false,
                onSelect = {},
                title = item.choiceIndex,
                toggleColorType = AnswerTileState.INCORRECT,
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.choiceText,
                        fontWeight = FontWeight(499),
                    )
                },
                indicateWithoutSelection = true
            )
            TestomaniaChoiceTile(
                selected = false,
                enabled = false,
                onSelect = {},
                title = item.correctAnswerIndex,
                toggleColorType = AnswerTileState.CORRECT,
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.correctAnswerText,
                        fontWeight = FontWeight(499),
                    )
                },
                indicateWithoutSelection = true
            )
        }
    }
}

@Preview
@Composable
fun MainResultItem(
    testName: String = "",
    mainColor: Color = OrbitTheme.colors.info.normal,
    progress: Float = 0.0f
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
            painter = painterResource(id = R.drawable.ic_driver_license),
            contentDescription = "Test icon",
            colorFilter = ColorFilter.tint(mainColor)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp, 0.dp)
        ) {
            Text(
                text = testName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = mainColor,
                fontSize = 12.sp,
                text = "${(progress * 10000).roundToInt() / 100}% correct"
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp)
                    .height(8.dp),
//                color = mainColor,
//                backgroundColor = lightColor
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp),
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = "no action",
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }
}