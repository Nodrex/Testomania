package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.quizapi.domain.model.QuizApiCategory
import com.earth.testomania.apis.quiz.quizapi.domain.repository.QuizRepository
import com.earth.testomania.quiz_categories.usecase.QuizApiBaseUrlUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


const val ROUTE_INFORMATIONAL_TECHNOLOGIES = "home/informational_technologies"

@Destination(route = ROUTE_INFORMATIONAL_TECHNOLOGIES)
@Composable
fun InformationalTechnologiesQuiz(navigator: DestinationsNavigator) {
    val viewModel: InformationalTechnologiesViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetInformationalTechnologiesUseCse @Inject constructor(private val repository: QuizRepository) :
    QuizApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuizList(QuizApiCategory.ALL)

}

@HiltViewModel
class InformationalTechnologiesViewModel @Inject constructor(
    useCase: GetInformationalTechnologiesUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)