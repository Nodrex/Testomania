package com.earth.testomania.driving_licence.domain.repository

import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion
import com.earth.testomania.util.Resource
import kotlinx.coroutines.flow.Flow

interface DrivingLicenceRepo {

    fun getQuestions(count: Int): Flow<Resource<List<DrivingLicenceQuestion>>>
}