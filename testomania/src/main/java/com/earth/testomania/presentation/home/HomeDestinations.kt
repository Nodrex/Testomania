package com.earth.testomania.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.earth.testomania.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

data class HomeDestinations(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    val destination: DirectionDestination? = null,
    val destination2: Direction? = null,
)