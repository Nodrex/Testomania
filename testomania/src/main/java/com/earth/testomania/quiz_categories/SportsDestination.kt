package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.quiz.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.OpenTDBApiBaseUrlUseCase
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import com.earth.testomania.common.unsplash.UnsplashRepo

const val ROUTE_SPORTS = "home/sports"

@Destination(route = ROUTE_SPORTS)
@Composable
fun SportsQuiz(navigator: DestinationsNavigator) {
    val viewModel: SportsViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetSportsUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.SPORTS, 20)

}

@HiltViewModel
class SportsViewModel @Inject constructor(
    useCase: GetSportsUseCse,
    dispatcher: CoroutineDispatcher, unsplashRepo: UnsplashRepo,
) : DestinationViewModel(
    useCase,
    dispatcher,
    unsplashRepo
)