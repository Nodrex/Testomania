package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.earth.testomania.R
import com.earth.testomania.core.helper.defaultTechQuiz
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.foundation.*

@Composable
fun CreateKiwiUI() {

    var selected by remember {
        mutableStateOf(false)
    }



        Column() {

            ChoiceTile(
                icon = {
                    painterResource(id = R.drawable.ic_correct)
                },
                selected = selected,
                onSelect = {
                    selected = !selected
                },
                showRadio = true,
                content = {
                    CreateQuizAnswerUI(defaultTechQuiz().possibleAnswers.firstNotNullOf {
                        it
                    })
                },

                )


        }






}

@Preview
@Composable
private fun Preview() {
    CreateKiwiUI()
}