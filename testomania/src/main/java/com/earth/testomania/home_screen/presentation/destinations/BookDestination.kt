package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTdbCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.home_screen.domain.usecase.GetQuizUseCase
import com.earth.testomania.technical.presentation.TechnicalTestsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_BOOK = "home/book"

@Destination(route = ROUTE_BOOK)
@Composable
fun BookQuiz(navigator: DestinationsNavigator) {
    val viewModel: BookViewModel = hiltViewModel()
    TechnicalTestsScreen(navigator, viewModel)
}

class GetBooksUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.BOOKS, 20)

}

@HiltViewModel
class BookViewModel @Inject constructor(
    useCase: GetBooksUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)


//FILM
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

