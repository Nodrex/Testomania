package com.earth.testomania.apis.opentdb.domain.repository

import com.earth.testomania.apis.opentdb.domain.models.OpenTdbCategory
import com.earth.testomania.common.DataState
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow

interface OpenTdbRepo {

    suspend fun getQuiz(
        category: OpenTdbCategory,
        questionCount: Int
    ): Flow<DataState<List<Quiz>>>

}
