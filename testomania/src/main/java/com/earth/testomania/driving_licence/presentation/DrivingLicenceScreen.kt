package com.earth.testomania.driving_licence.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(route = "home/driving_licence")
@Composable
fun DrivingLicenceScreen(
    navigator: DestinationsNavigator
) {

    Column {

        Text(text = "hey new comer 11!")

        Button(onClick = {
            Log.d("TAG", "DrivingLicenceScreen: Click")
        }) {
            Text(text = "next")
        }
    }
}
