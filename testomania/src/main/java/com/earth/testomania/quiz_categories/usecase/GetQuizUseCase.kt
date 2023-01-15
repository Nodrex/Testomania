package com.earth.testomania.quiz_categories.usecase

import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.data.ErrorMetaData
import com.earth.testomania.common.data.LoadingMetaData
import com.earth.testomania.common.data.SuccessMetaData
import com.earth.testomania.common.log
import com.earth.testomania.common.model.Quiz
import com.earth.testomania.common.model.QuizUIState
import kotlinx.coroutines.flow.*
import java.util.concurrent.CancellationException

abstract class GetQuizUseCase {

    abstract suspend fun getRepResult(): Flow<DataState<List<Quiz>>>

    abstract fun getAPIUrl(): String

    suspend operator fun invoke(): Flow<DataState<List<QuizUIState>>> =
        flow {
            try {
                emit(DataState.Loading(LoadingMetaData()))
                getRepResult().map {
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

    private fun success(list: List<Quiz>?): DataState.Success<List<QuizUIState>> {
        val quizUIStateList = mutableListOf<QuizUIState>()
        list?.forEach {
            if (it.correctAnswerCount == 0) logProblematicQuiz(it)
            else if (!it.hasMultiAnswer) quizUIStateList += QuizUIState(quiz = it)
            //TODO hasMultiAnswer filter is temporarily to disable multi answer quiz,
            // but we wel remove this constraint for future on next releases of App
        }
        return DataState.Success(
            SuccessMetaData(),
            quizUIStateList
        )
    }


    private fun error(dataState: DataState<List<Quiz>>) = DataState.Error(
        dataState.metaData as ErrorMetaData,
        emptyList<QuizUIState>()
    )

    private fun loading(dataState: DataState<List<Quiz>>) = DataState.Loading(
        dataState.metaData as LoadingMetaData,
        emptyList<QuizUIState>()
    )

    // TODO have a separate UseCase for reporting errors
    // UseCase should not do 2 things
    private fun logProblematicQuiz(quiz: Quiz) = log(
        """!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        |Problematic Quiz (Without correct answer) from: ${getAPIUrl()}
        |           $quiz
        |"[Please report to API creators]
        ------------------------------------------------"""
    )

}