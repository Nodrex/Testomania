package com.earth.testomania.driving_licence.domain.repository

import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion

interface DrivingLicenceRepo {

    suspend fun getQuestions(count: Int): List<DrivingLicenceQuestion>
}