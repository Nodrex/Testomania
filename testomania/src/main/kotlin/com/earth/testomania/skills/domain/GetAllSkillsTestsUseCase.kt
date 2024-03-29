package com.earth.testomania.skills.domain

import com.earth.testomania.skills.data.GeneralSkillsMathematicalRepo
import javax.inject.Inject

class GetAllSkillsTestsUseCase @Inject constructor(
    private val repo: GeneralSkillsMathematicalRepo,
) {

    fun execute(): List<GeneralMathematicalSkillsTest> {
        return repo.getAllTests().map { skillTest ->
            GeneralMathematicalSkillsTest(
                question = skillTest.question,
                possibleAnswers = skillTest.options.map { option ->
                    PossibleAnswer(text = option.option, isCorrect = option.correct == 1)
                }
            )
        }
    }
}