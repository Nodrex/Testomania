package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.quiz.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.OpenTDBApiBaseUrlUseCase
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_VIDEO_GAMES = "home/video_games"

@Destination(route = ROUTE_VIDEO_GAMES)
@Composable
fun VideoGamesQuiz(navigator: DestinationsNavigator) {
    val viewModel: VideoGamesViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetVideoGamesUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.VIDEO_GAMES, 20)

}

@HiltViewModel
class VideoGamesViewModel @Inject constructor(
    useCase: GetVideoGamesUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)