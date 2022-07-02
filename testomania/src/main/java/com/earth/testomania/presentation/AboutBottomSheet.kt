package com.earth.testomania.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kiwi.orbit.compose.ui.controls.Card

@Composable
fun CreateAboutBottomSheet() {

    Card(onClick = { }) {

        Text(text = "BottomSheet")

    }

    /*Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_explanation),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Some information to explain")
    }*/
}