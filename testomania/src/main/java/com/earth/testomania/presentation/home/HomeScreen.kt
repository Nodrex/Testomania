package com.earth.testomania.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.earth.testomania.presentation.destinations.SkillzTestScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    route = "home",
    start = true
)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    Column(Modifier.padding(start = 22.dp)) {
        Button(
            onClick = {
                navigator.navigate(SkillzTestScreenDestination())
            }) {
            Text(text = "უნარების ტესტები")
        }
        Button(
            onClick = {

            }) {
            Text(text = "მარვის მოწმობის ტესტები")
        }
        Button(
            onClick = {

            }) {
            Text(text = "გეოგრაფიის ტესტები")
        }
    }
}