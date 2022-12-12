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

const val ROUTE_POLITICS = "home/politics"

@Destination(route = ROUTE_POLITICS)
@Composable
fun PoliticsQuiz(navigator: DestinationsNavigator) {
    val viewModel: PoliticsViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetPoliticsUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.POLITICS, 20)

}

@HiltViewModel
class PoliticsViewModel @Inject constructor(
    useCase: GetPoliticsUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)