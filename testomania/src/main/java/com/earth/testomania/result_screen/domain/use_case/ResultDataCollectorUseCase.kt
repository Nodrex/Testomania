package com.earth.testomania.result_screen.domain.use_case

import com.earth.testomania.R
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.result_screen.domain.model.IncorrectlyAnsweredQuizModel
import com.earth.testomania.result_screen.domain.model.ResultData

class ResultDataCollectorUseCase {

    fun getTechnicalTestResult(
        techQuizList: List<QuizUIState>,
        quizCategoryName: String
    ): ResultData {

        val correctProgress = techQuizList.count {
            it.overallScore > 0
        } / techQuizList.size.toFloat()

        val resultData = ResultData(
            R.drawable.ic_orbit_dashboard,
            quizCategoryName,
            correctProgress,
            correctProgress > 0.5,
            techQuizList
                .filter {
                    it.selectedAnswers.isNotEmpty() && it.overallScore == 0.0
                }
                .map { wrappedQuizItem ->
                    val incorrectAnswerTag = wrappedQuizItem.selectedAnswers.firstOrNull()
                    val incorrectAnswer = wrappedQuizItem.quiz.answers.firstOrNull {
                        it.tag == incorrectAnswerTag?.selectedTag
                    }

                    IncorrectlyAnsweredQuizModel(
                        wrappedQuizItem.quiz,
                        wrappedQuizItem.quiz.answers.firstOrNull { it.isCorrect }
                            ?.let { listOf(it) } ?: listOf(),
                        incorrectAnswer?.let { listOf(it) } ?: listOf()
                    )
                }
        )
        return resultData
    }

}