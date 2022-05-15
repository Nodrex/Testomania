package com.earth.testomania.driving_licence.data.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

class MoshiParser(
    private val moshi: Moshi
): JsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {
        val adapter: JsonAdapter<T> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        val adapter: JsonAdapter<T> = moshi.adapter(type)
        return adapter.toJson(obj)
    }
}