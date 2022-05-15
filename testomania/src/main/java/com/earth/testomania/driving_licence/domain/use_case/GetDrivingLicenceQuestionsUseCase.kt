package com.earth.testomania.driving_licence.domain.use_case

import com.earth.testomania.core.DataState
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDrivingLicenceQuestionsUseCase(
    private val converter: AnswersListConverter,
    private val repository: DrivingLicenceRepo
) {

    operator fun invoke(): Flow<DataState<List<DrivingLicenceQuestion>>> = flow {
        emit(DataState.Loading())
        emit(DataState.Success(
            null,
            repository.getQuestions(DEFAULT_QUESTION_COUNT)
                .map { it.toDomainModel(converter) }
        ))
    }

    companion object {
        const val DEFAULT_QUESTION_COUNT = 30
    }
}