package com.earth.testomania.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.earth.testomania.presentation.destinations.DirectionDestination

data class HomeDestinations(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    val destination: DirectionDestination,
)