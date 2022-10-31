@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.technical.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import com.earth.testomania.common.custom_ui_components.DialogCloseAngle
import com.earth.testomania.common.custom_ui_components.RightPointingAngle
import com.earth.testomania.technical.domain.model.QuizCategory
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope

//TODO this dialog should be Generic and moved to home_screen package
@Composable
fun CategorySelectorBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    callBack: (QuizCategory) -> Unit
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 16.dp),
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.select_category_title)
    )

    LazyColumn {
        items(QuizCategory.values().toList()) {

            ChoiceTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    ),
                showRadio = false,
                selected = false,
                largeHeading = false,
                onSelect = {
                    callBack(it)
                },
                content = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            textAlign = TextAlign.Center,
                            text = it.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        RightPointingAngle()
                    }
                })
        }
    }

    DialogCloseAngle(scope, modalBottomSheetState)
}