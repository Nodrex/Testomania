package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.opentdb.OpenTdbCategory
import com.earth.testomania.opentdb.OpenTdbRepo
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kiwi.orbit.compose.ui.controls.Text
import javax.inject.Inject

//BOOKS
const val ROUTE = "home/book"

@Destination(route = ROUTE)
@Composable
fun BookQuiz(navigator: DestinationsNavigator) {
    val viewModel: BookViewModel = hiltViewModel()

    Text("Book Quiz here")

//    TechnicalTestsScreen(navigator, )
}

@HiltViewModel
class BookViewModel @Inject constructor(
    private val openTdbCategory: OpenTdbRepo
) : DestinationViewModel() {
    val category = OpenTdbCategory.BOOKS


}
//FILM
//MUSIC
//MUSICALS_AND_THEATRES
//TELEVISION
//VIDEO_GAMES
//BOARD_GAMES
//NATURE
//COMPUTERS
//MATHEMATICS
//MYTHOLOGY
//SPORTS
//GEOGRAPHY
//HISTORY
//POLITICS
//ART
//CELEBRITIES
//ANIMALS
//VEHICLES
//COMICS
//GADGETS
//ANIME_MANGA

