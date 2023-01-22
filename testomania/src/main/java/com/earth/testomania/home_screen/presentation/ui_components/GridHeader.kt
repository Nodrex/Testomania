package com.earth.testomania.home_screen.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.earth.testomania.R

@Composable
fun GridHeader(halfScreenHeight: Dp) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(halfScreenHeight),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.il_testomania),
        contentDescription = null
    )
}
