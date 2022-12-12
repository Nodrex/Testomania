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
            destinationWithParam = MainQuizScreenDestination
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
            R.string.books,
            icon = R.drawable.ic_orbit_bookmark,
            destination = BookQuizDestination
        ),
        HomeDestinationItem(
            R.string.music,
            icon = R.drawable.ic_orbit_musical_instruments,
            destination = MusicQuizDestination
        ),
        HomeDestinationItem(
            R.string.tv,
            icon = R.drawable.ic_orbit_ticket,
            destination = TVQuizDestination
        ),
        HomeDestinationItem(
            R.string.animals,
            icon = R.drawable.ic_orbit_alert,
            destination = AnimalsQuizDestination
        ),
        HomeDestinationItem(
            R.string.art,
            icon = R.drawable.ic_orbit_user_group,
            destination = ArtQuizDestination
        ),
        HomeDestinationItem(
            R.string.board_games,
            icon = R.drawable.ic_orbit_boarding_gate,
            destination = BoardGamesQuizDestination
        ),
        HomeDestinationItem(
            R.string.celebrities,
            icon = R.drawable.ic_orbit_cocktail,
            destination = CelebritiesQuizDestination
        ),
        HomeDestinationItem(
            R.string.comics,
            icon = R.drawable.ic_orbit_compass,
            destination = ComicsQuizDestination
        ),
        HomeDestinationItem(
            R.string.gadgets,
            icon = R.drawable.ic_orbit_ai,
            destination = GadgetsQuizDestination
        ),
        HomeDestinationItem(
            R.string.geography,
            icon = R.drawable.ic_orbit_playground,
            destination = GeographyQuizDestination
        ),
        HomeDestinationItem(
            R.string.history,
            icon = R.drawable.ic_orbit_history,
            destination = HistoryQuizDestination
        ),
        HomeDestinationItem(
            R.string.math,
            icon = R.drawable.ic_math,
            destination = MathQuizDestination
        ),
        HomeDestinationItem(
            R.string.movies,
            icon = R.drawable.ic_orbit_ticket,
            destination = MoviesQuizDestination
        ),
        HomeDestinationItem(
            R.string.mythology,
            icon = R.drawable.ic_orbit_color_picker,
            destination = MythQuizDestination
        ),
        HomeDestinationItem(
            R.string.nature,
            icon = R.drawable.ic_outline_info,
            destination = NatureQuizDestination
        ),
        HomeDestinationItem(
            R.string.politics,
            icon = R.drawable.ic_orbit_admin,
            destination = PoliticsQuizDestination
        ),
        HomeDestinationItem(
            R.string.sports,
            icon = R.drawable.ic_orbit_sports,
            destination = SportsQuizDestination
        ),
        HomeDestinationItem(
            R.string.vehicles,
            icon = R.drawable.ic_orbit_car,
            destination = VehiclesQuizDestination
        ),
        HomeDestinationItem(
            R.string.video_games,
            icon = R.drawable.ic_orbit_gallery,
            destination = VideoGamesQuizDestination
        ),
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