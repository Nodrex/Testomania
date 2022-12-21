package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.quiz.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.OpenTDBApiBaseUrlUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_MUSICALS_AND_THEATRES = "home/musicals_and_theaters"

@Destination(route = ROUTE_MUSICALS_AND_THEATRES)
@Composable
fun MusicalsAndTheatersQuiz(navigator: DestinationsNavigator) {
    val viewModel: MusicalsAndTheatersViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetMusicalsAndTheatersUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() =
        repository.getQuiz(OpenTDBApiCategory.MUSICALS_AND_THEATRES, 20)

}

@HiltViewModel
class MusicalsAndTheatersViewModel @Inject constructor(
    useCase: GetMusicalsAndTheatersUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)