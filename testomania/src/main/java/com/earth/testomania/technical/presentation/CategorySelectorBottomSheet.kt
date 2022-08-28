@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.technical.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import com.earth.testomania.technical.domain.model.QuizCategory
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun CategorySelectorBottomSheet(
    callBack: (QuizCategory) -> Unit
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        text = stringResource(id = R.string.select_category_title)
    )
    LazyColumn {
        QuizCategory.values().toList().forEach {
            item {
                ButtonSecondary(
                    onClick = {
                        callBack(it)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center

                    )
                }
            }
        }
    }

}