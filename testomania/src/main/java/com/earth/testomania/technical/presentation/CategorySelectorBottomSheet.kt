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
import com.earth.testomania.common.custom_ui_components.DialogCloseArrow
import com.earth.testomania.technical.domain.model.QuizCategory
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Radio
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope

@Composable
fun CategorySelectorBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
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
        items(QuizCategory.values().toList()) {

            ChoiceTile(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    ),
                showRadio = false,
                selected = false,
                onSelect = {
                    callBack(it)
                }) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        text = it.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Radio(
                        selected = false,
                        onClick = { }
                    )
                }
            }
        }
    }

    DialogCloseArrow(scope, modalBottomSheetState)
}