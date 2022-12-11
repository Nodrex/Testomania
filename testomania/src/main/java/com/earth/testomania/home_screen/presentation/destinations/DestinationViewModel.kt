package com.earth.testomania.home_screen.presentation.destinations

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.DataState
import com.earth.testomania.common.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.common.model.SelectedAnswer
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

    fun saveAnswer(quizUIState: QuizUIState, answerTag: Char) {
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

    private fun saveQuizPoint(quizUIState: QuizUIState, answerTag: Char) {
        if (quizUIState.quiz.hasMultiAnswer) return
        if (isCorrectAnswer(quizUIState, answerTag)) {
            quizUIState.receivedScore = quizUIState.quiz.point
            overallScore += quizUIState.receivedScore
        }
    }

    fun isCorrectAnswer(
        quizUIState: QuizUIState,
        answerTag: Char,
    ) = quizUIState.quiz.answers.firstOrNull {
        it.tag == answerTag
    }?.isCorrect ?: false


    fun wasAlreadyAnswered(quizUIState: QuizUIState, answerTag: Char) =
        quizUIState.selectedAnswers.find { it.selectedTag == answerTag } != null ||
                (quizUIState.selectedAnswers.isNotEmpty() &&
                        !quizUIState.quiz.hasMultiAnswer &&
                        isCorrectAnswer(
                            quizUIState,
                            answerTag
                        ))

//    fun getCategoryName() = category.tag.ifEmpty {
//        if (category.category == QuizCategory.Programing.category) {
//            "Programing"
//        } else category.category
//    }

    fun enableAnswerSelection(quizUIState: QuizUIState) =
        /*if (quizUIState.quiz.hasMultiAnswer) !quizUIState.multiSelectionWasDone.value else*/
        quizUIState.selectedAnswers.isEmpty()
}