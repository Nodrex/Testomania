package com.earth.testomania

import com.earth.testomania.tests.general.GeneralSkillsMathematicalRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class GeneralSkillsTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: GeneralSkillsMathematicalRepo

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `can read general_ability_test_data json file`() {
        assert(repo.getAllTests().size == 40)
    }

    @Test
    fun `get 10 random tests`() {
        val count = 10

        assert(repo.getRandomTests(count).size == count)
    }
}