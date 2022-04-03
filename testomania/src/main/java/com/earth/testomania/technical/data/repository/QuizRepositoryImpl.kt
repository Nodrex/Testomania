package com.earth.testomania.technical.data.repository

import com.earth.testomania.core.*
import com.earth.testomania.technical.data.source.remote.QuizApi
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuizList(): Flow<DataState<List<TechQuizDTO>>> = flow {
        try {
            emit(DataState.Loading(LoadingMetaData()))
            val result = quizApi.getQuizList()
            if (result.isSuccessful) {
                emit(DataState.Success(SuccessMetaData(), payload = result.body()))
            } else {
                emit(DataState.Error(ErrorMetaData(null)))
            }
        } catch (e: Exception) {
            emit(DataState.Error(ErrorMetaData(e)))
        }

    }

}