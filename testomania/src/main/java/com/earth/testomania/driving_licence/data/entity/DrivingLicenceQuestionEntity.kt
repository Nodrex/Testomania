package com.earth.testomania.driving_licence.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion


@Entity(tableName = "drivinglicencequestionentity")
data class DrivingLicenceQuestionEntity(
    @PrimaryKey val id: Int,
    val answers: String,
    val description: String?,
    val image: String?,
    val question: String,
) {
    fun toDomainModel(typeConverter: AnswersListConverter) = DrivingLicenceQuestion(
        id = id,
        answers = typeConverter.fromJsonToAnswers(answers),
        description = description,
        image = image,
        question = question,
    )
}