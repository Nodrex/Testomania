package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTdbCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.home_screen.domain.usecase.GetQuizUseCase
import com.earth.testomania.technical.presentation.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_ANIMALS = "home/animals"

@Destination(route = ROUTE_ANIMALS)
@Composable
fun AnimalsQuiz(navigator: DestinationsNavigator) {
    val viewModel: AnimalsQuizViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetAnimalsQuizUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.ANIMALS, 20)

}

@HiltViewModel
class AnimalsQuizViewModel @Inject constructor(
    useCase: GetAnimalsQuizUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)