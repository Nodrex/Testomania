package com.earth.testomania.quiz_categories.usecase

import com.earth.testomania.apis.opentdb.di.OPEN_TDB_API_BASE_URL

abstract class OpenTDBApiBaseUrlUseCase : GetQuizUseCase() {

    override fun getAPIUrl() = OPEN_TDB_API_BASE_URL

}