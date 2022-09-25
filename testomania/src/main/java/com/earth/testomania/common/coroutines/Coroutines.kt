package com.earth.testomania.common.coroutines

import com.earth.testomania.common.Crashlytics
import kotlinx.coroutines.CoroutineExceptionHandler

val defaultCoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("coroutine exception handler => $throwable")
    Crashlytics.recordException(throwable)
}