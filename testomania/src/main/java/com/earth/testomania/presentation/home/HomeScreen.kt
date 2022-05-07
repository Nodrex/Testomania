package com.earth.testomania.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.earth.testomania.R
import com.earth.testomania.presentation.destinations.SkillzTestScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kiwi.orbit.compose.ui.controls.Card

@Destination(
    route = "home",
    start = true
)
@Preview
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null,
) {
    Column(Modifier
        .padding(22.dp)
        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(15.dp)) {

        CardButton(R.string.general_skills_tests, R.drawable.ic_math) {
            navigator?.navigate(SkillzTestScreenDestination())
        }

        CardButton(R.string.driving_license_tests, R.drawable.ic_driver_license) {

        }

        CardButton(R.string.geography_tests, R.drawable.ic_geo) {
            // navigate
        }
    }
}

@Composable
fun CardButton(
    @StringRes text: Int = R.string.general_skills_tests,
    @DrawableRes icon: Int = R.drawable.ic_math,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Row(Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Image(painter = painterResource(id = icon),
                modifier = Modifier.size(20.dp),
                contentDescription = null)
            Text(text = stringResource(id = text),
                Modifier.padding(start = 10.dp))
            Image(painter = painterResource(id = R.drawable.ic_forward),
                modifier = Modifier.size(20.dp),
                contentDescription = null)
        }
    }
}