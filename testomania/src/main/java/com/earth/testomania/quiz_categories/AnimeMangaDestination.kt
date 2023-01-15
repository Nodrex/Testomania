package com.earth.testomania.quiz_categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.apis.quiz.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.quiz.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.quiz_categories.usecase.OpenTDBApiBaseUrlUseCase
import com.earth.testomania.quiz_screen.MainQuizScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val ROUTE_ANIME_MANGA = "home/anime_manga"

@Destination(route = ROUTE_ANIME_MANGA)
@Composable
fun AnimeMangaQuiz(navigator: DestinationsNavigator) {
    val viewModel: AnimeMangaViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetAnimeMangaUseCse @Inject constructor(
    private val repository: OpenTdbRepo
) : OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() = repository.getQuiz(OpenTDBApiCategory.ANIME_MANGA, 20)

}

@HiltViewModel
class AnimeMangaViewModel @Inject constructor(
    useCase: GetAnimeMangaUseCse, dispatcher: CoroutineDispatcher
) : DestinationViewModel(useCase, dispatcher)