package com.earth.testomania.presentation.home

import androidx.lifecycle.ViewModel
import com.earth.testomania.R
import com.earth.testomania.presentation.destinations.DummyScreenDestination
import com.earth.testomania.presentation.destinations.SkillzTestScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

) : ViewModel() {
    val destinations = listOf(
        HomeDestinations(
            name = R.string.general_skills_tests,
            icon = R.drawable.ic_math,
            destination = SkillzTestScreenDestination
        ),
        HomeDestinations(
            name = R.string.geography_tests,
            icon = R.drawable.ic_geo,
            destination = DummyScreenDestination
        ),
        HomeDestinations(
            name = R.string.driving_license_tests,
            icon = R.drawable.ic_driver_license,
            destination = DummyScreenDestination
        )
    )
}