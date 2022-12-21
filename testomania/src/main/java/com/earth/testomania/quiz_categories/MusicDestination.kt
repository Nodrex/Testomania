package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
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
    MainQuizScreen(navigator, viewModel)
}

class GetMusicsUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.MUSIC, 20)

}

@HiltViewModel
class MusicViewModel @Inject constructor(
    useCase: GetMusicsUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)