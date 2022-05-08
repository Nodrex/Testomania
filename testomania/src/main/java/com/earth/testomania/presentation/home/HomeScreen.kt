package com.earth.testomania.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import com.earth.testomania.destinations.DrivingLicenceScreenDestination
import com.earth.testomania.destinations.SkillzTestScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    route = "home",
    start = true
)
@Preview
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null,
) {
    Column(Modifier.padding(start = 22.dp)) {
        Button(
            onClick = {
                navigator?.navigate(SkillzTestScreenDestination())
            }) {
            Text(text = stringResource(id = R.string.general_skills_tests))
        }
        Button(
            onClick = {
                navigator?.navigate(DrivingLicenceScreenDestination())
            }) {
            Text(text = stringResource(id = R.string.driving_license_tests))
        }
        Button(
            onClick = {

            }) {
            Text(text = stringResource(id = R.string.geography_tests))
        }
    }
}