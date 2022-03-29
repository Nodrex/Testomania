package com.earth.testomania

import com.earth.testomania.tests.general.GeneralSkillsMathematicalRepo
import org.junit.Test

class GeneralSkillsTest {

    @Test
    fun `can read general_ability_test_data json file`() {
        val repo = GeneralSkillsMathematicalRepo()

        assert(repo.getAllTests().isNotEmpty())
    }

    @Test
    fun `get 10 random tests`() {
        val count = 10
        val repo = GeneralSkillsMathematicalRepo()

        assert(repo.getRandomTests(count).size == count)
    }
}