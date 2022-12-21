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

const val ROUTE_ART = "home/art"

@Destination(route = ROUTE_ART)
@Composable
fun ArtQuiz(navigator: DestinationsNavigator) {
    val viewModel: ArtViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetArtUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.ART, 20)

}

@HiltViewModel
class ArtViewModel @Inject constructor(
    useCase: GetArtUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)