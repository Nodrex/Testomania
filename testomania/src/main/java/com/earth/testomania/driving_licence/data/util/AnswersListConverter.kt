package com.earth.testomania.driving_licence.data.util

import com.earth.testomania.driving_licence.data.dto.Answer
import com.squareup.moshi.Types

class AnswersListConverter(private val jsonParser: JsonParser) {

    fun fromJsonToAnswers(json: String): List<Answer> {
        return jsonParser.fromJson<List<Answer>>(
            json = json,
            type = Types.newParameterizedType(List::class.java, Answer::class.java)
        ) ?: emptyList()
    }

    fun fromAnswersToJson(answers: List<Answer>): String {
        return jsonParser.toJson(
            obj = answers,
            type = Types.newParameterizedType(List::class.java, Answer::class.java)
        ) ?: "[]"
    }
}