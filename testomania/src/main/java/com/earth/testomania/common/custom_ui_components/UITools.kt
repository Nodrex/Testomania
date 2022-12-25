package com.earth.testomania.common.custom_ui_components

import androidx.compose.material.ScaffoldState

fun dismissSnackbar(scaffoldState: ScaffoldState?) {
    scaffoldState?.snackbarHostState?.currentSnackbarData?.dismiss()
}