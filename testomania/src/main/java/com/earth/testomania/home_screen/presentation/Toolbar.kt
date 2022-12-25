package com.earth.testomania.home_screen.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.earth.testomania.R

@Composable
fun Toolbar(lazyGridState: LazyGridState, halfScreenHeight: Dp) {


    /*Image(
        modifier = Modifier
            *//*.constrainAs(illustration) {
                top.linkTo(parent.top)
                bottom.linkTo(grid.top)
            }*//*
            .fillMaxWidth()
            .height(screenHeight/2),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.il_testomania),
        contentDescription = null
    )*/


/*    modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary)
        .animateContentSize(animationSpec = tween(durationMillis = 300))
        .height(height = if (lazyListState.isScrolled) 0.dp else TOP_BAR_HEIGHT)
    ,
    contentPadding = PaddingValues(start = 16.dp)*/

    Image(
        modifier = Modifier
            .fillMaxWidth()
            //.background(color = MaterialTheme.colors.primary)
            .animateContentSize(animationSpec = tween(durationMillis = 300))
            .height(height = if (lazyGridState.isScrolled) 0.dp else halfScreenHeight),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.il_testomania),
        contentDescription = null
    )
}
