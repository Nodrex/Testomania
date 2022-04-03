package com.earth.testomania.core

import java.lang.Exception

/**
 * TODO will explain little later
 */
sealed class DataState<out G, T>(val metaData: G? = null, val payload: T? = null) {
    class Loading<out G, T>(metaData: G? = null, payload: T? = null) :
        DataState<G, T>(metaData, payload)

    class Success<out G, T>(metaData: G? = null, payload: T? = null) :
        DataState<G, T>(metaData, payload)

    class Error<out G, T>(metaData: G? = null, payload: T? = null) : DataState<G, T>(metaData, payload)
}

//for extendability to use as SOLID principles
sealed class MetaData
open class LoadingData : MetaData()
open class SuccessData : MetaData()
open class ErrorData(exception: Exception? = null) : MetaData()


