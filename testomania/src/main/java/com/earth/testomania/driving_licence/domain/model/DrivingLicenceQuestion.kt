package com.earth.testomania.driving_licence.domain.model

import com.earth.testomania.driving_licence.data.dto.Answer

data class DrivingLicenceQuestion(
    val answers: List<Answer>,
    val description: String?,
    val id: Int,
    val image: String?,
    val question: String,
)
