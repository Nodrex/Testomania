package com.earth.testomania.driving_licence.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.earth.testomania.driving_licence.data.dto.Answer
import com.earth.testomania.driving_licence.domain.model.DrivingLicenceQuestion


@Entity(tableName = "drivinglicencequestionentity")
data class DrivingLicenceQuestionEntity(
    @PrimaryKey val id: Int,
    val answers: List<Answer>,
    val description: String?,
    val image: String?,
    val question: String,
) {
    fun toDomainModel() = DrivingLicenceQuestion(
        id = id,
        answers = answers,
        description = description,
        image = image,
        question = question,
    )
}