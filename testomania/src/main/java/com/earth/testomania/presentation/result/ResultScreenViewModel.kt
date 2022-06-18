package com.earth.testomania.presentation.result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(): ViewModel() {

    val isTestDone = true

    val incorrectQuestions = listOf(
        IncorrectAnsweredQuestionModel(
            "123",
            "Are you free?",
            "B",
            "Nope",
            "A",
            "Yes",
            "you just thinking that you are free!"
        ),
        IncorrectAnsweredQuestionModel(
            "1223",
            "How are you?",
            "C",
            "Good",
            "F",
            "I'm better then everyone in the world. However no one knows about it. Because I decided so",
        )
    )
}