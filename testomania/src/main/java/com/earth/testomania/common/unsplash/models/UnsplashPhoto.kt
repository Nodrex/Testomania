package com.earth.testomania.common.unsplash.models

data class UnsplashPhoto(
    val id: String?,
    val color: String?,
    val created_at: String?,
    val description: String?,
    val width: Int?,
    val height: Int?,
    val urls: UnsplashPhotoUrls
)