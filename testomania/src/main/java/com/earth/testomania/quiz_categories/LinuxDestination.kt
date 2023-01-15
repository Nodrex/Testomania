package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.quizapi.domain.model.QuizApiCategory
import com.earth.testomania.apis.quiz.quizapi.domain.repository.QuizRepository
import com.earth.testomania.quiz_categories.usecase.QuizApiBaseUrlUseCase
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_LINUX = "home/linux"

@Destination(route = ROUTE_LINUX)
@Composable
fun LinuxQuiz(navigator: DestinationsNavigator) {
    val viewModel: LinuxViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetLinuxUseCse @Inject constructor(private val repository: QuizRepository) :
    QuizApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuizList(QuizApiCategory.Linux)

}

@HiltViewModel
class LinuxViewModel @Inject constructor(
    useCase: GetLinuxUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)