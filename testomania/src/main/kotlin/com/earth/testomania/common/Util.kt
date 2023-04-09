package com.earth.testomania.common

import android.os.Bundle
import android.util.Log
import com.earth.testomania.BuildConfig
import com.earth.testomania.common.model.Quiz
import com.earth.testomania.common.model.QuizUIState
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

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
        log(exception.message ?: "Exception in Testomania Debug Mode")
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

fun sendFeedbackWithFirebaseAnalytics(text: String, quizUIState: QuizUIState) {
    firebaseAnalytics.logEvent("USER_FEEDBACK", Bundle().apply {
        putString("user_input", text)
        putString("quiz", quizUIState.quiz.toJsonString())
    })
}

fun Quiz.toJsonString(): String? {
    try {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<Quiz> = moshi.adapter(Quiz::class.java)
        return adapter.toJson(this)
    } catch (e: Exception) {
        log(e)
    }
    return null
}

