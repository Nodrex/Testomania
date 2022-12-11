package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.earth.testomania.common.model.QuizUIState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class DestinationViewModel : ViewModel() {
    private var getQuizListJob: Job? = null

    private val _data = mutableStateListOf<QuizUIState>()
    val data: List<QuizUIState> = _data

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    var overallScore: Double = 0.0
        private set

}