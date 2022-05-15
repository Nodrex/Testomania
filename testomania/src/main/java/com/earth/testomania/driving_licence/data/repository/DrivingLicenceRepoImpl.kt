package com.earth.testomania.driving_licence.data.repository

import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo

class DrivingLicenceRepoImpl(
    private val dao: DrivingLicenceDao
) : DrivingLicenceRepo {

    override suspend fun getQuestions(count: Int): List<DrivingLicenceQuestionEntity> {
        return dao.getQuestionsForTest(count = count)
    }
}