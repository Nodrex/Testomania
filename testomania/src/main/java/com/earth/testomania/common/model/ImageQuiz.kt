package com.earth.testomania.common.model

import android.content.Context
import coil.request.ImageRequest

sealed class ImageQuiz {

    abstract fun getImage(context: Context): ImageRequest

    fun createImageRequestBuilder(context: Context, any: Any?) =
        ImageRequest.Builder(context)
            .data(any)
            .crossfade(true)
            .build()

    data class Url(val url: String) : ImageQuiz() {
        override fun getImage(context: Context) = createImageRequestBuilder(context, url)
    }

    data class Bitmap(val bitmap: android.graphics.Bitmap) : ImageQuiz() {
        override fun getImage(context: Context) = createImageRequestBuilder(context, bitmap)
    }

    object None : ImageQuiz() {
        override fun getImage(context: Context) = createImageRequestBuilder(context, null)
    }

}