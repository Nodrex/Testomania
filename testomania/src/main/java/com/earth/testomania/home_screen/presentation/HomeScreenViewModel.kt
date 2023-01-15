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
            icon = R.drawable.about,
            destination = AboutBottomSheetDestination
        ),
        HomeDestinationItem(
            name = R.string.technical_tests,
            icon = R.drawable.it,
            destination = InformationalTechnologiesQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.linux,
            icon = R.drawable.linux,
            destination = LinuxQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.devops,
            icon = R.drawable.devops,
            destination = DevOpsQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.docker,
            icon = R.drawable.docker,
            destination = DockerQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.html,
            icon = R.drawable.html,
            destination = HTMLQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.my_sql,
            icon = R.drawable.mysql,
            destination = MySqlQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.php,
            icon = R.drawable.php,
            destination = PHPQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.programing,
            icon = R.drawable.programming,
            destination = ProgramingQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.sql,
            icon = R.drawable.database,
            destination = SQLQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.anime_manga,
            icon = R.drawable.anime,
            destination = AnimeMangaQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.computers,
            icon = R.drawable.computers,
            destination = ComputersQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.general_knowledge,
            icon = R.drawable.knowelage,
            destination = GeneralKnowledgeQuizDestination
        ),
        HomeDestinationItem(
            name = R.string.music_and_theater,
            icon = R.drawable.theatre,
            destination = MusicalsAndTheatersQuizDestination
        ),
        HomeDestinationItem(
            R.string.books,
            icon = R.drawable.book,
            destination = BookQuizDestination
        ),
        HomeDestinationItem(
            R.string.music,
            icon = R.drawable.music,
            destination = MusicQuizDestination
        ),
        HomeDestinationItem(
            R.string.tv,
            icon = R.drawable.tv,
            destination = TVQuizDestination
        ),
        HomeDestinationItem(
            R.string.animals,
            icon = R.drawable.animal_koala,
            destination = AnimalsQuizDestination
        ),
        HomeDestinationItem(
            R.string.art,
            icon = R.drawable.art,
            destination = ArtQuizDestination
        ),
        HomeDestinationItem(
            R.string.board_games,
            icon = R.drawable.board_game,
            destination = BoardGamesQuizDestination
        ),
        HomeDestinationItem(
            R.string.celebrities,
            icon = R.drawable.celebrities,
            destination = CelebritiesQuizDestination
        ),
        HomeDestinationItem(
            R.string.comics,
            icon = R.drawable.comics,
            destination = ComicsQuizDestination
        ),
        HomeDestinationItem(
            R.string.gadgets,
            icon = R.drawable.gadget,
            destination = GadgetsQuizDestination
        ),
        HomeDestinationItem(
            R.string.geography,
            icon = R.drawable.geography,
            destination = GeographyQuizDestination
        ),
        HomeDestinationItem(
            R.string.history,
            icon = R.drawable.history,
            destination = HistoryQuizDestination
        ),
        HomeDestinationItem(
            R.string.math,
            icon = R.drawable.math,
            destination = MathQuizDestination
        ),
        HomeDestinationItem(
            R.string.movies,
            icon = R.drawable.movies,
            destination = MoviesQuizDestination
        ),
        HomeDestinationItem(
            R.string.mythology,
            icon = R.drawable.mythology,
            destination = MythQuizDestination
        ),
        HomeDestinationItem(
            R.string.nature,
            icon = R.drawable.nature,
            destination = NatureQuizDestination
        ),
        HomeDestinationItem(
            R.string.politics,
            icon = R.drawable.politics,
            destination = PoliticsQuizDestination
        ),
        HomeDestinationItem(
            R.string.sports,
            icon = R.drawable.sports,
            destination = SportsQuizDestination
        ),
        HomeDestinationItem(
            R.string.vehicles,
            icon = R.drawable.vehilces,
            destination = VehiclesQuizDestination
        ),
        HomeDestinationItem(
            R.string.video_games,
            icon = R.drawable.video_games,
            destination = VideoGamesQuizDestination
        ),
    )

}