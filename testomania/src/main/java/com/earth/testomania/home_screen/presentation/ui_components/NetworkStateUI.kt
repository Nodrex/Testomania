package com.earth.testomania.home_screen.presentation.ui_components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import com.earth.testomania.common.networking.ConnectivityObserver
import com.earth.testomania.common.networking.NetworkConnectivityObserver
import com.earth.testomania.ui.theme.DarkRed
import com.earth.testomania.ui.theme.LightRed
import kiwi.orbit.compose.ui.controls.Text

/**
 * shows whether network is turned on or not to the user
 */
@Composable
fun NetworkStateManager(
    networkConnectivityObserver: NetworkConnectivityObserver,
) {
    val status by networkConnectivityObserver.observe().collectAsState(initial = false)

    if (status == ConnectivityObserver.ConnectionState.Available) return

    Pulsating {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, top = 80.dp, end = 26.dp, bottom = 0.dp)
                .wrapContentHeight()
                .background(
                    color = if (isSystemInDarkTheme()) LightRed else DarkRed,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_wifi_off_white),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = stringResource(R.string.check_your_connection),
                fontSize = 19.sp,
            )
        }
    }
}

@Composable
fun Pulsating(pulseFraction: Float = 0.98f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = pulseFraction, animationSpec = infiniteRepeatable(
            animation = tween(1000), repeatMode = RepeatMode.Reverse
        )
    )

    //TODO i think we do not need it and
    // we can directly use Row instead of box
    // we need to send modifier from here to content
    Box(modifier = Modifier.scale(scale)) {
        content()
    }
}
