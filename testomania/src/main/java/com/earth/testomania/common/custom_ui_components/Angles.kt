package com.earth.testomania.common.custom_ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import kiwi.orbit.compose.ui.controls.Icon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogCloseAngle(scope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState) {
    Icon(
        modifier = Modifier
            .height(40.dp)
            .padding(bottom = 10.dp, start = 100.dp, end = 100.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            {
                scope.launch {
                    modalBottomSheetState.hide()
                }
            },
        painter = painterResource(id = R.drawable.ic_orbit_chevron_down),
        contentDescription = "",
    )
}

@Composable
fun RightPointingAngle() {
    Icon(
        modifier = Modifier
            .size(20.dp),
        painter = painterResource(id = R.drawable.ic_orbit_chevron_right),
        contentDescription = "",
    )
}