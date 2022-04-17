package com.earth.testomania.driving_licence.data.dto

import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity
import com.earth.testomania.driving_licence.data.util.AnswersListConverter

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
    fun toDrivingLicenceEntity(typeConverter: AnswersListConverter): DrivingLicenceQuestionEntity {
        return DrivingLicenceQuestionEntity(
            id = id,
            answers = typeConverter.fromAnswersToJson(answers),
            description = description,
            image = image,
            question = question
        )
    }
}