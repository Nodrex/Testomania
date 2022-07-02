package com.earth.testomania.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import kiwi.orbit.compose.ui.controls.ChoiceTile

@Composable
fun CreateAboutBottomSheet() {

    Text(text = stringResource(id = R.string.about_app))

    AboutDeveloper(name = "Giorgi Shalvashvili")
    AboutDeveloper(name = "Kartlos Diakonidze")
    AboutDeveloper(name = "Nodar Tchumbadze")
    AboutDeveloper(name = "Nika mgaloblishvili")
    AboutDeveloper(name = "Lika Glonti")

    /*Card(onClick = { }) {

        Text(text = "BottomSheet")

    }*/

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

@Composable
fun AboutDeveloper(name: String) {
    ChoiceTile(
        modifier = Modifier.padding(
            start = 10.dp,
            end = 10.dp,
            bottom = 10.dp
        ),
        selected = false,
        showRadio = false,
        title = {
            Text(text = name)
        },
        onSelect = {

        }
    )
}