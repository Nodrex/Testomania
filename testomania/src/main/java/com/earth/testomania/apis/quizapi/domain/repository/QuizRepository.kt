package com.earth.testomania.apis.quizapi.domain.repository

import com.earth.testomania.apis.quiz.quizapi.domain.model.QuizApiCategory
import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    suspend fun getQuizList(params: QuizApiCategory): Flow<DataState<List<Quiz>>>

}