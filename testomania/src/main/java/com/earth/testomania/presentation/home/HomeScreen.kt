@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.earth.testomania.presentation.ABOUT_ROUT
import com.earth.testomania.presentation.AboutBottomSheet
import com.earth.testomania.presentation.dummy.DUMMY_ROUT
import com.earth.testomania.skills.presentation.skillz.SKILLZ_ROUT
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Icon
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Destination(
    route = "home",
    start = true
)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null,
) {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            AboutBottomSheet()
        }) {
        HomeScreenContent(
            navigator,
            scaffoldState,
            bottomSheetState
        )
    }

}

@Composable
fun HomeScreenContent(
    navigator: DestinationsNavigator?,
    scaffoldState: BottomSheetScaffoldState,
    bottomSheetState: BottomSheetState
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val (grid, illustration) = createRefs()

        val contentPadding = 20.dp
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .constrainAs(grid) {
                    bottom.linkTo(parent.bottom)
                }
                .systemBarsPadding(),
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
                    bottomSheetState
                )
            }
        }

        Image(
            modifier = Modifier
                .constrainAs(illustration) {
                    bottom.linkTo(grid.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.il_testomania),
            contentDescription = null
        )
    }
}

@Composable
fun CardButton(
    destinationInfo: HomeDestinations,
    navigator: DestinationsNavigator? = null,
    scaffoldState: BottomSheetScaffoldState,
    bottomSheetState: BottomSheetState
) {
    val scope = rememberCoroutineScope()
    val comingSoonStr = stringResource(id = R.string.coming_soon)

    Card(modifier = Modifier.size(125.dp), shape = RoundedCornerShape(10.dp), onClick = {
        when (destinationInfo.destination.route) {
            SKILLZ_ROUT, DUMMY_ROUT -> {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(comingSoonStr)
                }
                return@Card
            }
            ABOUT_ROUT -> {
                scope.launch {
                    if (bottomSheetState.isCollapsed) bottomSheetState.expand()
                    else bottomSheetState.collapse()
                }
            }
            else -> navigator?.navigate(destinationInfo.destination)
        }
    }) {
        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
