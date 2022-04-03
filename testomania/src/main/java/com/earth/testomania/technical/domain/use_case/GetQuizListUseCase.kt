package com.earth.testomania.technical.domain.use_case

import com.earth.testomania.core.DataState
import com.earth.testomania.core.MetaData
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuizListUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(): Flow<DataState<MetaData, List<TechQuizDTO>>> {
        return quizRepository.getQuizList()
    }

}