package com.earth.testomania.skills

import android.content.Context
import com.earth.testomania.R
import com.earth.testomania.skills.dto.GeneralTestItemDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.hilt.android.qualifiers.ApplicationContext
import okio.buffer
import okio.source
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralSkillsMathematicalRepo @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val moshi: Moshi,
) {

    @OptIn(ExperimentalStdlibApi::class)
    private val tests by lazy {
        val rawJson = appContext.resources.openRawResource(R.raw.general_ability_test_data)
        val adapter: JsonAdapter<List<GeneralTestItemDTO>> = moshi.adapter()
        adapter.fromJson(rawJson.source().buffer()) ?: emptyList()
    }

    fun getAllTests(): List<GeneralTestItemDTO> {
        return tests
    }

    fun getRandomTests(count: Int): List<GeneralTestItemDTO> {
        return List(count) { tests.random() }
    }
}