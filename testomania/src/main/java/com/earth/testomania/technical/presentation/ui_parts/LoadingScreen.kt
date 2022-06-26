package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.earth.testomania.core.presentation.custom.TestomaniaInlineLoading
import com.earth.testomania.technical.domain.model.TechCategory
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun CreateLoadingScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val (progressBar, illustration) = createRefs()

        TestomaniaInlineLoading(modifier = Modifier
            .constrainAs(progressBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(top = 50.dp),
            circleSize = 18.dp,
            distanceBetweenCircles = 10.dp
        )

        var techCategory by remember {
            mutableStateOf(TechCategory.UNKNOWN)
        }

        LaunchedEffect(key1 = true) {
            while (true) {
                techCategory = TechCategory.values().random()
                delay(TimeUnit.SECONDS.toMillis(2))
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
            contentDescription = techCategory.strValue
        )
    }
}