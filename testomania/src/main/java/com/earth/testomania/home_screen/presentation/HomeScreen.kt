@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.home_screen.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.earth.testomania.destinations.TechnicalTestsScreenDestination
import com.earth.testomania.home_screen.domain.model.HomeDestinationItem
import com.earth.testomania.technical.presentation.CategorySelectorBottomSheet
import com.earth.testomania.ui.theme.DialogBkgDark
import com.earth.testomania.ui.theme.DialogBkgLight
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = if (isSystemInDarkTheme()) DialogBkgDark else DialogBkgLight,
        scrimColor = Color.Transparent,
        sheetContent = {
            Column(Modifier.navigationBarsPadding()) {
                SheetLayout(modalBottomSheetState, scope, navigator)
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .systemBarsPadding(),
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent
        ) {
            HomeScreenContent(
                navigator,
                scaffoldState,
                modalBottomSheetState
            )
        }
    }
}

@Composable
fun SheetLayout(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    navigator: DestinationsNavigator?
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val pageType by viewModel.bottomSheetPageState.collectAsState(initial = BottomSheetScreen.Technical)
    BottomContent(pageType, modalBottomSheetState, scope, navigator)
}

@Composable
fun BottomContent(
    pageType: BottomSheetScreen,
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    navigator: DestinationsNavigator?
) {
    when (pageType) {
        is BottomSheetScreen.Technical -> CategorySelectorBottomSheet(
            modalBottomSheetState,
            scope
        ) { quizCategory ->
            scope.launch {
                modalBottomSheetState.snapTo(ModalBottomSheetValue.Hidden)
                //snapTo is quicker then hide
            }.invokeOnCompletion {
                navigator?.navigate(TechnicalTestsScreenDestination())
                //we need to navigate to other screen after bottom-sheet is close,
                // otherwise bottom-sheet remains open when returning to home screen
            }
        }
        else -> AboutBottomSheet(modalBottomSheetState, scope)
    }
}

@Composable
fun HomeScreenContent(
    navigator: DestinationsNavigator?,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (grid, illustration) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(illustration) {
                    top.linkTo(parent.top)
                    bottom.linkTo(grid.top)
                }
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.il_testomania),
            contentDescription = null
        )

        val contentPadding = 20.dp
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .constrainAs(grid) {
                    bottom.linkTo(parent.bottom)
                },
            contentPadding = PaddingValues(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(contentPadding),
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            items(viewModel.destinations.size) { index ->
                val item = viewModel.destinations[index]
                CardButton(
                    item,
                    navigator,
                    scaffoldState,
                    modalBottomSheetState
                )
            }
        }
    }
}

@Composable
fun CardButton(
    destinationInfo: HomeDestinationItem,
    navigator: DestinationsNavigator? = null,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
) {
    val scope = rememberCoroutineScope()
    val comingSoonStr = stringResource(id = R.string.coming_soon)
    val dismissStr = stringResource(id = R.string.dismiss)
    val viewModel: HomeScreenViewModel = hiltViewModel()



    SurfaceCard (modifier = Modifier.size(125.dp), shape = RoundedCornerShape(10.dp), onClick = {

        dismissCurrentSnackbar(scaffoldState)

        navigator?.navigate(destinationInfo.destination ?: return@SurfaceCard)

//        when (destinationInfo.destination?.route ?: destinationInfo.destinationWithParam?.route) {
//            SKILLZ_ROUTE, DUMMY_ROUTE -> {
//                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = comingSoonStr,
//                        actionLabel = dismissStr,
//                    )
//                }
//                return@SurfaceCard
//            }
//            ABOUT_ROUT -> {
//                viewModel.onBottomSheetPageChange(BottomSheetScreen.About)
//                scope.launch {
//                    modalBottomSheetState.show()
//                }
//            }
//            "$TECHNICAL_ROUTE/ALL" -> {
//                viewModel.onBottomSheetPageChange(BottomSheetScreen.Technical)
//                scope.launch {
//                    modalBottomSheetState.show()
//                }
//            }
//        }
    }) {
        Column(
            Modifier
                .padding(10.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val name = stringResource(id = destinationInfo.name)
            Icon(
                painter = painterResource(id = destinationInfo.icon),
                contentDescription = name,
                Modifier.size(40.dp)
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = name, textAlign = TextAlign.Center)
            }
        }
    }
}

private fun dismissCurrentSnackbar(scaffoldState: ScaffoldState) {
    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
}
