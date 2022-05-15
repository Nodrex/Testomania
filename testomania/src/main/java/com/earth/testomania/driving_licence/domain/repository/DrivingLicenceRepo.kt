package com.earth.testomania.driving_licence.domain.repository

import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity

interface DrivingLicenceRepo {

    suspend fun getQuestions(count: Int): List<DrivingLicenceQuestionEntity>
}