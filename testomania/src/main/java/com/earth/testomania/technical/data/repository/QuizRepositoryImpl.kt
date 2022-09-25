package com.earth.testomania.technical.data.repository

import com.earth.testomania.common.DataState
import com.earth.testomania.common.ErrorMetaData
import com.earth.testomania.common.SuccessMetaData
import com.earth.testomania.technical.data.source.remote.QuizApi
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuizList(): Flow<DataState<List<TechQuizDTO>>> = flow {
        quizApi.getQuizList().apply {
            if (isSuccessful) emit(DataState.Success(SuccessMetaData(), payload = body()))
            else emit(DataState.Error(ErrorMetaData(null)))
        }
    }

}