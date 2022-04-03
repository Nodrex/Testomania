package com.earth.testomania.driving_licence.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.earth.testomania.driving_licence.data.dto.Answer
import com.squareup.moshi.Types

@ProvidedTypeConverter
class Converters(private val jParser: JsonParser) {

    @TypeConverter
    fun fromJsonToAnswers(json: String): List<Answer> {
        return jParser.fromJson<List<Answer>>(
            json = json,
            type = Types.newParameterizedType(List::class.java, Answer::class.java)
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromAnswersToJson(answers: List<Answer>): String {
        return jParser.toJson(
            obj = answers,
            type = Types.newParameterizedType(List::class.java, Answer::class.java)
        ) ?: "[]"
    }
}