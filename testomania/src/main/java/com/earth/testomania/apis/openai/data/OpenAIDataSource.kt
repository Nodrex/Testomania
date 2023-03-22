package com.earth.testomania.apis.openai.data

interface OpenAIDataSource {
    suspend fun getAnswer(question: String): String
}
