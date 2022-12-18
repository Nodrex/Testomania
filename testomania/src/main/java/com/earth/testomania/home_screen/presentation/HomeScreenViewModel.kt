package com.earth.testomania.home_screen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.DataState
import com.earth.testomania.common.networking.NetworkConnectivityObserver
import com.earth.testomania.common.unsplash.UnsplashRepo
import com.earth.testomania.destinations.AboutBottomSheetDestination
import com.earth.testomania.destinations.DummyScreenDestination
import com.earth.testomania.destinations.SkillzTestScreenDestination
import com.earth.testomania.destinations.TechnicalTestsScreenDestination
import com.earth.testomania.home_screen.domain.model.HomeDestinations
import com.earth.testomania.technical.domain.model.QuizCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val networkObserver: NetworkConnectivityObserver,
    val unsplashRepo: UnsplashRepo,
) : ViewModel() {

    private val _bottomSheetPageState = MutableSharedFlow<BottomSheetScreen>()
    val bottomSheetPageState = _bottomSheetPageState.asSharedFlow()

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
            _bottomSheetPageState.emit(newPage)
        }
    }

    init {
        viewModelScope.launch {
            delay(2222)
            unsplashRepo.getPhoto("programing").collectLatest {
                when (it) {
                    is DataState.Success ->
                        Log.d("TAG", "PHOTO: ${it.payload}")
                    is DataState.Error ->
                        Log.d("TAG", "PHOTO ERROR: ${it.payload}")
                    else -> {}
                }
            }
        }
    }
}

sealed class BottomSheetScreen {
    object Technical : BottomSheetScreen()
    object About : BottomSheetScreen()
}