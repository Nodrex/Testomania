package com.earth.testomania.common.unsplash

import com.earth.testomania.common.unsplash.UnsplashApi.Companion.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UnsplashModule {

    @Provides
    @Singleton
    fun provideUnsplashApi(okHttpClient: OkHttpClient, moshi: Moshi): UnsplashApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun provideUnsplashRepository(api: UnsplashApi): UnsplashRepo {
        return UnsplashRepo(api)
    }
}