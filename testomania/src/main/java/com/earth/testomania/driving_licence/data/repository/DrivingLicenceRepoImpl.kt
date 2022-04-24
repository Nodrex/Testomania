package com.earth.testomania.driving_licence.data.repository

import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo

class DrivingLicenceRepoImpl(
    private val converter: AnswersListConverter,
    private val dao: DrivingLicenceDao
) : DrivingLicenceRepo {

    override suspend fun getQuestions(count: Int): List<DrivingLicenceQuestion> {
        return dao.getQuestionsForTest(count = count)
            .map { it.toDomainModel(converter) }
    }
}