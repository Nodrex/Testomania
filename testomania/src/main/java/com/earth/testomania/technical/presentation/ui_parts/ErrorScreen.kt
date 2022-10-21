package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import kiwi.orbit.compose.ui.controls.Text

@Preview
@Composable
fun ErrorScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.il_connection_error),
            contentDescription = ""
        )

        Text(
            text = stringResource(id = R.string.error_load),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}