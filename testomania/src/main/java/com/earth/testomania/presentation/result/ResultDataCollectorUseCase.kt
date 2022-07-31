package com.earth.testomania.presentation.result

import com.earth.testomania.R
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
                .map { qItem ->
                    val incIndex = qItem.selectedAnswers.find {
                        it.userSelected
                    }?.selectedKey ?: ""
                    val correctIndex = qItem.quiz.correctAnswers.filter {
                        it.value
                    }.keys.first()

                    IncorrectAnsweredQuestionModel(
                        qItem.quiz.id.toString(),
                        qItem.quiz.question,
                        correctIndex,
                        qItem.quiz.possibleAnswers[correctIndex] ?: "",
                        incIndex,
                        qItem.quiz.possibleAnswers[incIndex] ?: ""
                    )
                }
        )
        return resultData
    }
}