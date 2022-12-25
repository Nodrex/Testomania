package com.earth.testomania.home_screen.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.earth.testomania.common.custom_ui_components.dismissSnackbar
import com.earth.testomania.common.networking.ConnectivityObserver
import com.earth.testomania.common.networking.NetworkConnectivityObserver
import com.earth.testomania.home_screen.domain.model.HomeDestinationItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GridWithItems(
    viewModel: HomeScreenViewModel, navigator: DestinationsNavigator?,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
    lazyGridState: LazyGridState,
    halfScreenHeight: Dp
) {

    val padding by animateDpAsState(
        targetValue = if (lazyGridState.isScrolled) 0.dp else halfScreenHeight,
        animationSpec = tween(durationMillis = 300)
    )

   val contentPadding = 20.dp

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = padding),
        state = lazyGridState,
        /*.constrainAs(grid) {
            bottom.linkTo(parent.bottom)
        },*/
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
                modalBottomSheetState,
                viewModel.networkObserver
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardButton(
    destinationInfo: HomeDestinationItem,
    navigator: DestinationsNavigator? = null,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
    networkConnectivityObserver: NetworkConnectivityObserver,
) {
    val scope = rememberCoroutineScope()
    val status by networkConnectivityObserver.observe()
        .collectAsState(initial = false)

    SurfaceCard(
        modifier = Modifier.size(125.dp),
        shape = RoundedCornerShape(10.dp),
        enabled = status == ConnectivityObserver.ConnectionState.Available,
        onClick = {
            dismissSnackbar(scaffoldState)
            when (destinationInfo.destination?.route) {
                ABOUT_ROUT -> {
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                }
                else -> navigator?.navigate(destinationInfo.destination ?: return@SurfaceCard)
            }
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