package com.earth.testomania.technical.domain.repository

import com.earth.testomania.common.DataState
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    suspend fun getQuizList(): Flow<DataState<List<TechQuizDTO>>>

}