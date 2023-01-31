package com.earth.testomania.common.custom_ui_components

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun dismissSnackbar(scaffoldState: ScaffoldState?) {
    scaffoldState?.snackbarHostState?.currentSnackbarData?.dismiss()
}

fun showSnackbar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState?,
    message: String,
    actionLabel: String = ""
) {
    scope.launch {
        scaffoldState?.snackbarHostState?.showSnackbar(
            message = message,
            actionLabel = actionLabel
        )
    }
}