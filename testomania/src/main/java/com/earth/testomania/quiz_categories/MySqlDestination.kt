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

const val ROUTE_MYSQL = "home/mySql"

@Destination(route = ROUTE_MYSQL)
@Composable
fun MySqlQuiz(navigator: DestinationsNavigator) {
    val viewModel: MySqlViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetMySqlUseCse @Inject constructor(private val repository: QuizRepository) :
    GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuizList(QuizApiCategory.MySql)

}

@HiltViewModel
class MySqlViewModel @Inject constructor(
    useCase: GetMySqlUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)