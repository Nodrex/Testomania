package com.earth.testomania.driving_licence.data.dto

import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity

data class DrivingLicenceQuestionDto(
    val answers: List<Answer>,
    val categoryIds: List<Int>,
    val description: String?,
    val id: Int,
    val image: String?,
    val imageType: Int?,
    val question: String,
    val subjectId: Int?
) {
    fun toDrivingLicenceEntity(): DrivingLicenceQuestionEntity {
        return DrivingLicenceQuestionEntity(
            id = id,
            answers = answers,
            description = description,
            image = image,
            question = question
        )
    }
}