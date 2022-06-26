package com.earth.testomania.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.earth.testomania.presentation.dummy.DUMMY_ROUT
import com.earth.testomania.skills.presentation.skillz.SKILLZ_ROUT
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Icon

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
                CardButton(item, navigator)
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
) {
    Card(modifier = Modifier.size(125.dp), shape = RoundedCornerShape(10.dp), onClick = {
        when (destinationInfo.destination.route) {
            SKILLZ_ROUT, DUMMY_ROUT -> return@Card
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
