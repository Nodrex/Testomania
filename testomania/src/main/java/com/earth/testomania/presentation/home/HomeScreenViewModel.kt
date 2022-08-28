package com.earth.testomania.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.destinations.AboutBottomSheetDestination
import com.earth.testomania.destinations.DummyScreenDestination
import com.earth.testomania.destinations.SkillzTestScreenDestination
import com.earth.testomania.destinations.TechnicalTestsScreenDestination
import com.earth.testomania.technical.domain.model.QuizCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    val bottomSheetPageState: MutableStateFlow<BottomSheetScreen> =
        MutableStateFlow(BottomSheetScreen.Technical)

    val destinations = listOf(
        HomeDestinations(
            name = R.string.about,
            icon = R.drawable.ic_outline_info,
            destination = AboutBottomSheetDestination
        ),
        HomeDestinations(
            name = R.string.technical_tests,
            icon = R.drawable.ic_orbit_dashboard,
            destinationWithParam = TechnicalTestsScreenDestination(QuizCategory.ALL)
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

    fun onBottomSheetPageChange(newPage: BottomSheetScreen) {
        viewModelScope.launch {
            bottomSheetPageState.emit(newPage)
        }
    }
}

sealed class BottomSheetScreen {
    object Technical : BottomSheetScreen()
    object About : BottomSheetScreen()
}