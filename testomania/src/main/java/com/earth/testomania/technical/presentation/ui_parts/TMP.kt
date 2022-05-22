package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import kiwi.orbit.compose.ui.controls.BadgeInfoSubtle
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun CreateKiwiUI() {

    var selected by remember {
        mutableStateOf(false)
    }



    Column() {

        ChoiceTile(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = {
                Text(
                    "Multiline long choice title label",
                    modifier = Modifier.weight(1f)
                )
                BadgeInfoSubtle {
                    Text("Popular")
                }
            },
            description = { Text("Multiline and very long description and lot of words") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_correct),
                    contentDescription = null
                )
            },
            onSelect = {

            },
            selected = false
            )


    }


}

@Preview
@Composable
private fun Preview() {
    CreateKiwiUI()
}