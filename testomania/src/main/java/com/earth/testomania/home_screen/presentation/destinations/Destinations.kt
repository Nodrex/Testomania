package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.opentdb.OpenTdbCategory
import com.earth.testomania.opentdb.OpenTdbRepo
import com.earth.testomania.technical.presentation.TechnicalTestsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE = "home/book"

@Destination(route = ROUTE)
@Composable
fun BookQuiz(navigator: DestinationsNavigator) {
    val viewModel: BookViewModel = hiltViewModel()
    TechnicalTestsScreen(navigator, viewModel)
}

class GetBooksUseCse @Inject constructor(val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.BOOKS, 20)

}

@HiltViewModel
class BookViewModel @Inject constructor(
    private val useCase: GetBooksUseCse,
    private val dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)


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

