package com.earth.testomania.common

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

const val GENERIC_TAG = "TESTOMANIA_TAG"

fun log(text: String) {
    Log.d(GENERIC_TAG, text)
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