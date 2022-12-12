package com.earth.testomania.apis.quizapi.domain.repository

import com.earth.testomania.apis.quizapi.domain.model.QuizCategory
import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    suspend fun getQuizList(params: QuizCategory): Flow<DataState<List<Quiz>>>

}