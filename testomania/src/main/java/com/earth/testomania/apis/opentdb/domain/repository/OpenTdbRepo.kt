package com.earth.testomania.apis.opentdb.domain.repository

import com.earth.testomania.apis.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow

interface OpenTdbRepo {

    suspend fun getQuiz(
        category: OpenTDBApiCategory,
        questionCount: Int
    ): Flow<DataState<List<Quiz>>>

}
