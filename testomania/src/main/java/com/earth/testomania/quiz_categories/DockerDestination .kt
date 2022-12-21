package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quizapi.domain.model.QuizApiCategory
import com.earth.testomania.apis.quizapi.domain.repository.QuizRepository
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_DOCKER = "home/docker"

@Destination(route = ROUTE_DOCKER)
@Composable
fun DockerQuiz(navigator: DestinationsNavigator) {
    val viewModel: DockerViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetDockerUseCse @Inject constructor(private val repository: QuizRepository) :
    GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuizList(QuizApiCategory.Docker)

}

@HiltViewModel
class DockerViewModel @Inject constructor(
    useCase: GetDockerUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)