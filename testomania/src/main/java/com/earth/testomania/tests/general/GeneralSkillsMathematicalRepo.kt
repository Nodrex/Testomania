package com.earth.testomania.tests.general

import android.content.Context
import com.earth.testomania.R
import com.earth.testomania.tests.general.dto.GeneralTestItemDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralSkillsMathematicalRepo @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val json: Json,
) {

    private val tests by lazy {
        val rawJson = appContext.resources.openRawResource(R.raw.general_ability_test_data)
        json.decodeFromStream<List<GeneralTestItemDTO>>(rawJson)
    }

    fun getAllTests(): List<GeneralTestItemDTO> {
        return tests
    }

    fun getRandomTests(count: Int): List<GeneralTestItemDTO> {
        return List(count) { tests.random() }
    }
}