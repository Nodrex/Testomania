package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.common.unsplash.UnsplashRepo
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_BOARD_GAMES = "home/board_games"

@Destination(route = ROUTE_BOARD_GAMES)
@Composable
fun BoardGamesQuiz(navigator: DestinationsNavigator) {
    val viewModel: BoardGamesViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetBoardGamesUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.BOARD_GAMES, 20)

}

@HiltViewModel
class BoardGamesViewModel @Inject constructor(
    useCase: GetBoardGamesUseCse,
    dispatcher: CoroutineDispatcher, unsplashRepo: UnsplashRepo,

    ) : DestinationViewModel(
    useCase,
    dispatcher, unsplashRepo,
)