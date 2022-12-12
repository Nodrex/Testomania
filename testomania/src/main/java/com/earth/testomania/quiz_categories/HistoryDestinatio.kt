package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTdbCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_HISTORY = "home/history"

@Destination(route = ROUTE_HISTORY)
@Composable
fun HistoryQuiz(navigator: DestinationsNavigator) {
    val viewModel: HistoryViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetHistoryUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTdbCategory.HISTORY, 20)

}

@HiltViewModel
class HistoryViewModel @Inject constructor(
    useCase: GetHistoryUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)