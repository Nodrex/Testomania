package com.earth.testomania.presentation.result

import com.earth.testomania.R
import com.earth.testomania.core.log
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper

class ResultDataCollectorUseCase {

    fun getTechnicalTestResult(techQuizList: List<TechQuizItemWrapper>): ResultData {

        val correctProgress = techQuizList.count {
            it.point > 0
        } / techQuizList.size.toFloat()

        val resultData = ResultData(
            R.drawable.ic_orbit_dashboard,
            R.string.technical_tests,
            correctProgress,
            correctProgress > 0.5,
            techQuizList
                .filter {
                    it.selectedAnswers.isNotEmpty() && it.point == 0
                }
                .map { wrappedQuizItem ->
                    val incorrectIndex = getIncorrectIndex(wrappedQuizItem)
                    val correctIndex = getCorrectIndex(wrappedQuizItem)

                    IncorrectAnsweredQuestionModel(
                        wrappedQuizItem.quiz.id.toString(),
                        wrappedQuizItem.quiz.question,
                        correctIndex,
                        wrappedQuizItem.quiz.possibleAnswers[correctIndex] ?: "",
                        incorrectIndex,
                        wrappedQuizItem.quiz.possibleAnswers[incorrectIndex] ?: ""
                    )
                }
        )
        return resultData
    }

    private fun getIncorrectIndex(wrappedQuizItem: TechQuizItemWrapper): String =
        wrappedQuizItem.selectedAnswers.find {
            it.userSelected
        }?.selectedKey ?: ""

    private fun getCorrectIndex(wrappedQuizItem: TechQuizItemWrapper): String =
        try {
            wrappedQuizItem.quiz.correctAnswers.filter {
                it.value
            }.keys.first()
        } catch (e: NoSuchElementException) {
            log(e.stackTraceToString())
            ""
        }

}