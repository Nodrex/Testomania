package com.earth.testomania.core.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler

val defaultCoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("coroutine exception => $throwable")
}