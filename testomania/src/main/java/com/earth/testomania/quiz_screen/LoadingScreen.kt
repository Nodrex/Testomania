package com.earth.testomania.quiz_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.earth.testomania.apis.quizapi.domain.model.TechCategory
import kiwi.orbit.compose.ui.controls.LinearIndeterminateProgressIndicator
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun LoadingScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val (progressBar, illustration) = createRefs()

        LinearIndeterminateProgressIndicator(modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 3.dp, start = 10.dp, end = 10.dp)
            .height(15.dp)
            .constrainAs(progressBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        var techCategory by remember {
            mutableStateOf(TechCategory.UNKNOWN)
        }

        LaunchedEffect(key1 = true) {
            while (true) {
                techCategory = TechCategory.values().random()
                delay(2.seconds)
            }
        }

        Image(
            modifier = Modifier
                .constrainAs(illustration) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = techCategory.illustration),
            contentDescription = techCategory.category
        )
    }
}