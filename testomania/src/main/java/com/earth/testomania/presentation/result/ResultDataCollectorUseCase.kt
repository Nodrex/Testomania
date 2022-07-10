package com.earth.testomania.presentation.result

import com.earth.testomania.technical.domain.model.TechQuizItemWrapper

class ResultDataCollectorUseCase {

    fun getTechnicalTestResult(techQuizList: List<TechQuizItemWrapper>): ResultData {

        val progress = techQuizList.count { quizItem ->
            quizItem.selectedAnswers.isNotEmpty()
        } / techQuizList.size.toFloat()

        val resultData = ResultData(
            "TechnicalTests",
            progress,
            progress > 0.5,
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
                        qItem.quiz.possibleAnswers[correctIndex] ?: ""
                    )
                }
        )
        return resultData
    }
}