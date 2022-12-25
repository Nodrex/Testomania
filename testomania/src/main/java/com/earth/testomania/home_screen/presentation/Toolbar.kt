package com.earth.testomania.home_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.earth.testomania.R

@Composable
fun Toolbar(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp/2

    Image(
        modifier = Modifier
            /*.constrainAs(illustration) {
                top.linkTo(parent.top)
                bottom.linkTo(grid.top)
            }*/
            .fillMaxWidth()
            .height(screenHeight/2),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.il_testomania),
        contentDescription = null
    )
}
