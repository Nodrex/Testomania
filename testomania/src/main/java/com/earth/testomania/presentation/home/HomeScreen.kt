package com.earth.testomania.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Card

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

    @OptIn(ExperimentalFoundationApi::class)
    LazyVerticalGrid(cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(viewModel.destinations.size) { index ->
            val item = viewModel.destinations[index]
            CardButton(item, navigator)
        }
    }
}

@Composable
fun CardButton(
    destinationInfo: HomeDestinations,
    navigator: DestinationsNavigator? = null,
) {
    Card(modifier = Modifier.size(125.dp), shape = RoundedCornerShape(10.dp), onClick = {
        navigator?.navigate(destinationInfo.destination)
    }) {
        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = destinationInfo.icon),
                modifier = Modifier.size(40.dp),
                contentDescription = null)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = destinationInfo.name), textAlign = TextAlign.Center)
            }

        }
    }
}
