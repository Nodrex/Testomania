package com.earth.testomania.home_screen.presentation

import androidx.lifecycle.ViewModel
import com.earth.testomania.R
import com.earth.testomania.common.networking.NetworkConnectivityObserver
import com.earth.testomania.destinations.*
import com.earth.testomania.home_screen.domain.model.HomeDestinationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val networkObserver: NetworkConnectivityObserver,
) : ViewModel() {

    val destinations = listOf(
        HomeDestinationItem(
            name = R.string.about,
            icon = R.drawable.ic_outline_info,
            destination = AboutBottomSheetDestination
        ),
        HomeDestinationItem(
            name = R.string.technical_tests,
            icon = R.drawable.ic_orbit_dashboard,
            destination = InformationalTechnologiesQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.linux,
            icon = R.drawable.ic_orbit_location_e,
            destination = LinuxQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.devops,
            icon = R.drawable.ic_orbit_location_e,
            destination = DevOpsQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.docker,
            icon = R.drawable.ic_orbit_location_e,
            destination = DockerQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.html,
            icon = R.drawable.ic_orbit_location_e,
            destination = HTMLQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.my_sql,
            icon = R.drawable.ic_orbit_location_e,
            destination = MySqlQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.php,
            icon = R.drawable.ic_orbit_location_e,
            destination = PHPQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.programing,
            icon = R.drawable.ic_orbit_location_e,
            destination = ProgramingQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.sql,
            icon = R.drawable.ic_orbit_location_e,
            destination = SQLQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.anime_manga,
            icon = R.drawable.ic_math,
            destination = AnimeMangaQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.computers,
            icon = R.drawable.ic_math,
            destination = ComputersQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.general_knowledge,
            icon = R.drawable.ic_math,
            destination = GeneralKnowledgeQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.music_and_theater,
            icon = R.drawable.ic_math,
            destination = MusicalsAndTheatersQuizDestination
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

}