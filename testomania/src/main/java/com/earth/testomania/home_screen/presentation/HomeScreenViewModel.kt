package com.earth.testomania.home_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.destinations.*
import com.earth.testomania.home_screen.domain.model.HomeDestinationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _bottomSheetPageState = MutableSharedFlow<BottomSheetScreen>()
    val bottomSheetPageState = _bottomSheetPageState.asSharedFlow()

    val destinations = listOf(
        HomeDestinationItem(
            name = R.string.about,
            icon = R.drawable.ic_outline_info,
            destination = AboutBottomSheetDestination
        ),
        HomeDestinationItem(
            name = R.string.technical_tests,
            icon = R.drawable.ic_orbit_dashboard,
            destinationWithParam = TechnicalTestsScreenDestination()
        ),
        HomeDestinationItem(
            name = R.string.general_skills_tests,
            icon = R.drawable.ic_math,
            destination = SkillzTestScreenDestination
        ),
        HomeDestinationItem(
            name = R.string.driving_license_tests,
            icon = R.drawable.ic_driver_license,
            destination = DummyScreenDestination
        ),
        HomeDestinationItem(
            R.string.book,
            icon = R.drawable.ic_math,
            destination = BookQuizDestination
        )
    )

    fun onBottomSheetPageChange(newPage: BottomSheetScreen) {
        viewModelScope.launch {
            _bottomSheetPageState.emit(newPage)
        }
    }
}

sealed class BottomSheetScreen {
    object Technical : BottomSheetScreen()
    object About : BottomSheetScreen()
}