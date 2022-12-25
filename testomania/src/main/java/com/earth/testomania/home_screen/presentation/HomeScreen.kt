@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.home_screen.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.ui.theme.DialogBkgDark
import com.earth.testomania.ui.theme.DialogBkgLight
import com.earth.testomania.ui.theme.LightRed
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MaterialDesignInsteadOrbitDesign")
@Preview(showSystemUi = true)
@Destination(
    route = "home",
    start = true
)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null,
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    val lazyGridState = rememberLazyGridState()

    NetworkStateManager(viewModel.networkObserver)

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch {
            modalBottomSheetState.hide()
        }
    }

    //We need ModalBottomSheetLayout, because only this BottomSheet can be closed when clicking outside of bottomSheet
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = if (isSystemInDarkTheme()) DialogBkgDark else DialogBkgLight,
        scrimColor = Color.Transparent,
        sheetContent = {
            Column(Modifier.navigationBarsPadding()) {
                AboutBottomSheet(modalBottomSheetState, scope)
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .systemBarsPadding(),
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent
        ) {
            GridWithItems(viewModel, navigator, scaffoldState, modalBottomSheetState)
            Toolbar()

            /*HomeScreenContent(
                viewModel,
                navigator,
                scaffoldState,
                modalBottomSheetState
            )*/
        }
    }
}

/*@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel,
    navigator: DestinationsNavigator?,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
) {
    Toolbar()
    GridWithItems(viewModel, navigator, scaffoldState, modalBottomSheetState)
}*/


val LazyGridState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0


