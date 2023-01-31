package com.earth.testomania.home_screen.presentation.ui_components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.common.custom_ui_components.dismissSnackbar
import com.earth.testomania.ui.theme.LightDark
import com.earth.testomania.ui.theme.LightGray
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun CustomSnackBar(scaffoldState: ScaffoldState) {
    SnackbarHost(
        hostState = scaffoldState.snackbarHostState,
        snackbar = { data ->
            val fontSize = 14.sp
            Snackbar(
                backgroundColor = if (isSystemInDarkTheme()) LightDark else SnackbarDefaults.backgroundColor,
                modifier = Modifier.padding(14.dp),
                content = {
                    Text(
                        text = data.message,
                        fontSize = fontSize,
                        color = if (isSystemInDarkTheme()) LightGray else Color.Unspecified
                    )
                },
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = {
                            dismissSnackbar(scaffoldState)
                        }) {
                            Text(
                                text = actionLabel,
                                fontSize = fontSize,
                                color = MaterialTheme.colors.primary,
                            )
                        }
                    }
                }
            )
        }
    )
}
