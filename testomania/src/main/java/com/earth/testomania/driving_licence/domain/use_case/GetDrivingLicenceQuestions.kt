package com.earth.testomania.driving_licence.domain.use_case

import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo
import com.earth.testomania.util.Resource
import kotlinx.coroutines.flow.Flow

class GetDrivingLicenceQuestions(
    private val repository: DrivingLicenceRepo
) {

    operator fun invoke(): Flow<Resource<List<DrivingLicenceQuestion>>> {
        return repository.getQuestions(30)
    }
}