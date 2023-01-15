package com.earth.testomania.home_screen.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
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
import androidx.compose.ui.graphics.Color
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
    modifier: Modifier,
    viewModel: HomeScreenViewModel, navigator: DestinationsNavigator?,
    scaffoldState: ScaffoldState,
    modalBottomSheetState: ModalBottomSheetState,
    halfScreenHeight: Dp,
) {
    val contentPadding = 20.dp

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(contentPadding),
        verticalArrangement = Arrangement.spacedBy(contentPadding)
    ) {
        header {
            GridHeader(halfScreenHeight)
        }

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
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = name, textAlign = TextAlign.Center)
            }
        }
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}