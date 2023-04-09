package com.earth.testomania.quiz_categories.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.log
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.common.model.SelectedAnswer
import com.earth.testomania.common.sendFeedbackWithFirebaseAnalytics
import com.earth.testomania.quiz_categories.usecase.GetQuizUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class DestinationViewModel(
    open val getQuizListUseCase: GetQuizUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var getQuizListJob: Job? = null

    private val _data = mutableStateListOf<QuizUIState>()
    val data: List<QuizUIState> = _data

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    var overallScore: Double = 0.0
        private set

    init {
        initialize()
    }

    private fun initialize() {
        getQuizListJob?.cancel()
        getQuizListJob = viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase().catch {
                ensureActive()
                _error.emit(R.string.error_generic)
            }.collectLatest {
                ensureActive()
                when (it) {
                    is DataState.Success -> it.payload?.apply {
                        _data.addAll(this)
                    }

                    is DataState.Error -> _error.emit(R.string.error_load)
                    else -> {}
                }
            }
        }
    }

    fun saveAnswer(quizUIState: QuizUIState, answerTag: String) {
        saveQuizPoint(quizUIState, answerTag)
        val index = _data.indexOf(quizUIState)
        val newItem = _data.removeAt(index).let {
            it.copy(
                quiz = it.quiz,
                selectedAnswers = it.selectedAnswers.apply {
                    add(
                        SelectedAnswer(
                            answerTag,
                            true
                        )
                    )
                }
            )
        }
        _data.add(index, newItem)
    }

    private fun saveQuizPoint(quizUIState: QuizUIState, answerTag: String) {
        if (quizUIState.quiz.hasMultiAnswer) return
        if (isCorrectAnswer(quizUIState, answerTag)) {
            quizUIState.receivedScore = quizUIState.quiz.point
            overallScore += quizUIState.receivedScore
        }
    }

    fun isCorrectAnswer(
        quizUIState: QuizUIState,
        answerTag: String,
    ) = quizUIState.quiz.answers.firstOrNull {
        it.tag == answerTag
    }?.isCorrect ?: false


    fun wasAlreadyAnswered(quizUIState: QuizUIState, answerTag: String) =
        quizUIState.selectedAnswers.find { it.selectedTag == answerTag } != null ||
                (quizUIState.selectedAnswers.isNotEmpty() &&
                        !quizUIState.quiz.hasMultiAnswer &&
                        isCorrectAnswer(
                            quizUIState,
                            answerTag
                        ))

    fun enableAnswerSelection(quizUIState: QuizUIState) =
        quizUIState.selectedAnswers.isEmpty()

    fun sendFeedback(text: String) {
        if (text.isEmpty()) return
        sendFeedbackWithFirebaseAnalytics(text)
        log("Feedback sent: $text")
    }

}