package com.earth.testomania.common

import android.util.Log
import com.earth.testomania.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics

const val GENERIC_TAG = "TESTOMANIA_TAG"

fun log(text: String) {
    if (BuildConfig.DEBUG) {
        Log.d(GENERIC_TAG, text)
    } else {
        Crashlytics.log(text)
    }
}

fun log(exception: Exception) {
    if (BuildConfig.DEBUG) {
        exception.printStackTrace()
    } else {
        Crashlytics.recordException(exception)
    }
}

object Crashlytics {

    private fun getInstance() = FirebaseCrashlytics.getInstance()

    fun recordException(throwable: Throwable) {
        getInstance().recordException(throwable)
    }

    fun log(text: String) {
        getInstance().log(text)
    }

}

fun Any?.println() {
    println(this)
}