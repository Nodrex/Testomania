package com.earth.testomania.core

import java.lang.Exception

/**
 * TODO will explain little later
 */
sealed class DataState<T>(val data: T? = null) {
    class Loading<T>(data: T? = null) : DataState<T>(data)
    class Success<T>(data: T? = null) : DataState<T>(data)
    class Error<T>(errorData: ErrorData? = null, data: T? = null) : DataState<T>(data)
}

open class ErrorData(exception: Exception)
