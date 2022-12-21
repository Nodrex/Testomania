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

const val ROUTE_GENERAL_KNOWLEDGE = "home/general_knowledge"

@Destination(route = ROUTE_GENERAL_KNOWLEDGE)
@Composable
fun GeneralKnowledgeQuiz(navigator: DestinationsNavigator) {
    val viewModel: GeneralKnowledgeViewModel = hiltViewModel()
    MainQuizScreen(navigator, viewModel)
}

class GetGeneralKnowledgeUseCse @Inject constructor(private val repository: OpenTdbRepo) :
    OpenTDBApiBaseUrlUseCase() {

    override suspend fun getRepResult() =
        repository.getQuiz(OpenTDBApiCategory.GENERAL_KNOWLEDGE, 20)

}

@HiltViewModel
class GeneralKnowledgeViewModel @Inject constructor(
    useCase: GetGeneralKnowledgeUseCse,
    dispatcher: CoroutineDispatcher
) : DestinationViewModel(
    useCase,
    dispatcher
)