package com.earth.testomania.common

import android.os.Bundle
import android.util.Log
import com.earth.testomania.BuildConfig
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase

const val GENERIC_TAG = "TESTOMANIA_TAG"
val firebaseAnalytics = Firebase.analytics

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

fun sendFeedbackWithFirebaseAnalytics(text: String) {
    firebaseAnalytics.logEvent("USER_FEEDBACK", Bundle().apply {
        putString("user_input", text)
        putString("quiz", "here should be quiz")
    })
}

