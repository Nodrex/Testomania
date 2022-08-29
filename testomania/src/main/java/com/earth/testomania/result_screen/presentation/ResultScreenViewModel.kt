package com.earth.testomania.result_screen.presentation

import androidx.lifecycle.ViewModel
import com.earth.testomania.result_screen.domain.model.IncorrectAnsweredQuestionModel
import com.earth.testomania.result_screen.domain.model.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor() : ViewModel() {

    lateinit var resultData: ResultData


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