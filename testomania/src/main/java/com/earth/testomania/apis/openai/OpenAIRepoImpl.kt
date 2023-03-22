package com.earth.testomania.apis.openai

import com.earth.testomania.apis.openai.data.OpenAIDataSource
import javax.inject.Inject

class OpenAIRepoImpl @Inject constructor(
    private val openAIDataSource: OpenAIDataSource
) : OpenAIRepo {
    override suspend fun getAnswer(question: String) = openAIDataSource.getAnswer(question)
}
