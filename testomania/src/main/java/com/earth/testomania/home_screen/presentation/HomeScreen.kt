@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.home_screen.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.home_screen.presentation.ui_components.AboutBottomSheet
import com.earth.testomania.home_screen.presentation.ui_components.CustomSnackBar
import com.earth.testomania.home_screen.presentation.ui_components.GridWithItems
import com.earth.testomania.home_screen.presentation.ui_components.NetworkStateManager
import com.earth.testomania.ui.theme.DialogBkgDark
import com.earth.testomania.ui.theme.DialogBkgLight
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MaterialDesignInsteadOrbitDesign")
@Preview(showSystemUi = true)
@Destination(route = "home", start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null,
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch {
            modalBottomSheetState.hide()
        }
    }

    val configuration = LocalConfiguration.current
    val halfScreenHeight = configuration.screenHeightDp.dp / 2

    // We need ModalBottomSheetLayout, because only this BottomSheet can be closed when clicking
    // outside of bottomSheet
    ModalBottomSheetLayout(sheetState = modalBottomSheetState,
        sheetBackgroundColor = if (isSystemInDarkTheme()) DialogBkgDark else DialogBkgLight,
        scrimColor = Color.Transparent,
        sheetContent = {
            Column(Modifier.navigationBarsPadding()) {
                AboutBottomSheet(modalBottomSheetState, scope)
            }
        }) {
        Scaffold(
            modifier = Modifier.statusBarsPadding(),
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            snackbarHost = {
                CustomSnackBar(scaffoldState)
            },
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (grid, searchBar) = createRefs()

                GridWithItems(
                    Modifier.constrainAs(grid) {
                        top.linkTo(parent.top)
                        bottom.linkTo(searchBar.top)
                    },
                    viewModel,
                    navigator,
                    scaffoldState,
                    modalBottomSheetState,
                    halfScreenHeight,
                )
            }
        }
    }

    NetworkStateManager(viewModel.networkObserver)
}
