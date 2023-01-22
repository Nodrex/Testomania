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

const val ROUTE_VEHICLES = "home/vehicles"

@Destination(route = ROUTE_VEHICLES)
@Composable
fun VehiclesQuiz(navigator: DestinationsNavigator) {
    val viewModel: VehiclesViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetVehiclesUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.VEHICLES, 20)

}

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    useCase: GetVehiclesUseCse,
    dispatcher: CoroutineDispatcher, unsplashRepo: UnsplashRepo,
) : DestinationViewModel(
    useCase,
    dispatcher,
    unsplashRepo
)