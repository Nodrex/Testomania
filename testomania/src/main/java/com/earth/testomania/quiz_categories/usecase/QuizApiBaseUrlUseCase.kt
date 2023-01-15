package com.earth.testomania.quiz_categories.usecase

import com.earth.testomania.apis.quiz.quizapi.data.source.QUIZ_API_PATH
import com.earth.testomania.apis.quiz.quizapi.di.QUIZ_API_BASE_URL

abstract class QuizApiBaseUrlUseCase : GetQuizUseCase() {

    override fun getAPIUrl() = "$QUIZ_API_BASE_URL$QUIZ_API_PATH"

}