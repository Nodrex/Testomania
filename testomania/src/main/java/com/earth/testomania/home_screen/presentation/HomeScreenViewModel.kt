package com.earth.testomania.home_screen.presentation

import androidx.lifecycle.ViewModel
import com.earth.testomania.R
import com.earth.testomania.destinations.AboutBottomSheetDestination
import com.earth.testomania.destinations.DummyScreenDestination
import com.earth.testomania.destinations.SkillzTestScreenDestination
import com.earth.testomania.destinations.TechnicalTestsScreenDestination
import com.earth.testomania.home_screen.domain.model.HomeDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

) : ViewModel() {
    val destinations = listOf(
        HomeDestinations(
            name = R.string.about,
            icon = R.drawable.ic_outline_info,
            destination = AboutBottomSheetDestination
        ),
        HomeDestinations(
            name = R.string.technical_tests,
            icon = R.drawable.ic_orbit_dashboard,
            destination = TechnicalTestsScreenDestination
        ),
        HomeDestinations(
            name = R.string.general_skills_tests,
            icon = R.drawable.ic_math,
            destination = SkillzTestScreenDestination
        ),
        HomeDestinations(
            name = R.string.driving_license_tests,
            icon = R.drawable.ic_driver_license,
            destination = DummyScreenDestination
        )
    )
}