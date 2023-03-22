package com.earth.testomania.apis.openai

interface OpenAIRepo {
    suspend fun getAnswer(question: String): String
}
