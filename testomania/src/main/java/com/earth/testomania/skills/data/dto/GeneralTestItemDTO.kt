package com.earth.testomania.skills.data.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GeneralTestItemDTO(
//    val active: Int,
//    val creator_id: Any,
//    val description: Any,
//    val grade: Any,
    val id: Int,
    val options: List<OptionDTO>,
    val ordering: Int,
    val question: String,
//    val subject_id: Any,
    val test_id: Int,
//    val text_id: Any,
//    val type_id: Int
)