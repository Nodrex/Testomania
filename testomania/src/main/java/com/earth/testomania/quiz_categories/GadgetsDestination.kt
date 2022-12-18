package com.earth.testomania.quiz_categories
import com.earth.testomania.common.unsplash.UnsplashRepo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_GADGETS = "home/gadgets"

@Destination(route = ROUTE_GADGETS)
@Composable
fun GadgetsQuiz(navigator: DestinationsNavigator) {
    val viewModel: GadgetsViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetGadgetsUseCse @Inject constructor(private val repository: OpenTdbRepo) : GetQuizUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.GADGETS, 20)

}

@HiltViewModel
class GadgetsViewModel @Inject constructor(
    useCase: GetGadgetsUseCse,
    dispatcher: CoroutineDispatcher, unsplashRepo: UnsplashRepo,
) : DestinationViewModel(
    useCase,
    dispatcher, unsplashRepo,
)