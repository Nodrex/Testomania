package com.earth.testomania.technical.data.repository

import com.earth.testomania.core.DataState
import com.earth.testomania.core.ErrorData
import com.earth.testomania.technical.data.source.remote.QuizApi
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class QuizRepositoryImpl(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuizList(): Flow<DataState<List<TechQuizDTO>>> = flow {
        try {
            emit(DataState.Loading())
            quizApi.getQuizList()
            //TODO emit result
        } catch (e: Exception) {
            emit(DataState.Error(ErrorData(e)))
        }

    }

}