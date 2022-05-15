package com.earth.testomania.util

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<out T>(data: T?) : Resource<T>(data)
    class Error<T>(
        val code: Int = 0,
        message: String,
        data: T? = null
    ) : Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}