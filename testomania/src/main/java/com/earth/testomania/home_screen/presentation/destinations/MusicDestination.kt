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

const val ROUTE_MUSIC = "home/music"

@Destination(route = ROUTE_MUSIC)
@Composable
fun MusicQuiz(navigator: DestinationsNavigator) {
    val viewModel: MusicViewModel = hiltViewModel()
    TechnicalTestsScreen(navigator, viewModel)
}

class GetMusicsUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.MUSIC, 20)

}

@HiltViewModel
class MusicViewModel @Inject constructor(
    useCase: GetMusicsUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)