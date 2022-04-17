package com.earth.testomania.driving_licence.data.repository

import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo
import com.earth.testomania.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrivingLicenceRepoImpl(
    private val converter: AnswersListConverter,
    private val dao: DrivingLicenceDao
) : DrivingLicenceRepo {

    override fun getQuestions(count: Int): Flow<Resource<List<DrivingLicenceQuestion>>> = flow {
        emit(Resource.Loading())
        val questions = dao.getQuestionsForTest(count = count)
            .map { it.toDomainModel(converter) }

        emit(Resource.Success(questions))
    }
}