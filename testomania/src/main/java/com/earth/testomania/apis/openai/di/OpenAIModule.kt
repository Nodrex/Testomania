package com.earth.testomania.apis.openai.di

import com.earth.testomania.apis.openai.OpenAIRepo
import com.earth.testomania.apis.openai.OpenAIRepoImpl
import com.earth.testomania.apis.openai.data.OpenAIDataSource
import com.earth.testomania.apis.openai.data.RemoteOpenAIDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OpenAIModule {

    @Binds
    fun bindsOpenAIRepo(openAIRepoImpl: OpenAIRepoImpl): OpenAIRepo

    @Binds
    fun bindsOpenAIDataSource(openAIDataSource: RemoteOpenAIDataSource): OpenAIDataSource

}
