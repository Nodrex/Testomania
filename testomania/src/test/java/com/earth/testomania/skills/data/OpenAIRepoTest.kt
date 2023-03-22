package com.earth.testomania.skills.data

import com.earth.testomania.apis.openai.OpenAIRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class OpenAIRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: OpenAIRepo

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `should send message`() {
        runBlocking(Dispatchers.IO) {
            repo.getAnswer("Hello ChatGPT, How are you?")
        }
    }
}
