package com.earth.testomania.technical.domain.use_case

import com.earth.testomania.common.*
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.technical.data.repository.FALSE_STR
import com.earth.testomania.technical.data.source.remote.QUIZ_API_PATH
import com.earth.testomania.technical.data.source.remote.dto.TagDTO
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.di.QUIZ_API_BASE_URL
import com.earth.testomania.technical.domain.model.AnswerKey
import com.earth.testomania.technical.domain.model.QuizCategory
import com.earth.testomania.technical.domain.model.TechQuiz
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.*
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetQuizListUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(params: QuizCategory): Flow<DataState<List<QuizUIState>>> =
        flow {
            try {
                emit(DataState.Loading(LoadingMetaData()))
                quizRepository.getQuizList(params).map {
                    when (it) {
                        is DataState.Success -> emit(success(it.payload))
                        is DataState.Error -> emit(error(it))
                        is DataState.Loading -> emit(loading(it))
                    }
                }.catch {
                    emit(DataState.Error(ErrorMetaData(it.cause as Exception?)))
                }.collect()
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                emit(DataState.Error(ErrorMetaData(e)))
            }
        }

    private fun success(list: List<TechQuizDTO>?) =
        DataState.Success(
            SuccessMetaData(),
            list?.filter {
                it.multiple_correct_answers == FALSE_STR && hasCorrectAnswer(it)
                //TODO this filter is temporarily to disable multi answer quiz,
                // but we wel remove this constraint for future on next releases of App
            }?.mapNotNull { techQuiz ->
                convertTechQuizDTOtoDataObject(techQuiz)
            })

    //TODO maybe for later i will move this to extension
    private fun error(dataState: DataState<List<TechQuizDTO>>) = DataState.Error(
        dataState.metaData as ErrorMetaData,
        emptyList<QuizUIState>()
    )

    //TODO maybe for later i will move this to extension
    private fun loading(dataState: DataState<List<TechQuizDTO>>) = DataState.Loading(
        dataState.metaData as LoadingMetaData,
        emptyList<QuizUIState>()
    )


}