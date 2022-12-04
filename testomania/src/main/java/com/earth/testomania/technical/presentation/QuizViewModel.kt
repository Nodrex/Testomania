package com.earth.testomania.technical.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.DataState
import com.earth.testomania.common.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.common.model.SelectedAnswer
import com.earth.testomania.technical.domain.model.QuizCategory
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class QuizViewModel @AssistedInject constructor(
    @Assisted val category: QuizCategory,
    private val getQuizListUseCase: GetQuizListUseCase,
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
        getQuizList()
    }

    private fun getQuizList() {
        getQuizListJob?.cancel()
        getQuizListJob = viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase(category).catch {
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

    fun getCategoryName() = category.tag.ifEmpty {
        if (category.category == QuizCategory.Programing.category) {
            "Programing"
        } else category.category
    }

    fun enableAnswerSelection(quizUIState: QuizUIState) =
        /*if (quizUIState.quiz.hasMultiAnswer) !quizUIState.multiSelectionWasDone.value else*/
        quizUIState.selectedAnswers.isEmpty()

/*    fun multiSelectionWasDone(quizUIState: QuizUIState) {
        saveMultiSelectQuizPoint(quizUIState)
        quizUIState.multiSelectionWasDone.value = true
    }*/

/*    private fun saveMultiSelectQuizPoint(quizUIState: QuizUIState) {
        if (!quizUIState.quiz.hasMultiAnswer) return
        quizUIState.selectedAnswers.forEach {
            if (!isCorrectAnswer(quizUIState, it.selectedKey)) return
        }
        quizUIState.point = 1
    }*/

/*    fun getQuizResult() = data.sumOf {
        it.point
    }*/

    @AssistedFactory
    interface Factory {
        fun create(cat: QuizCategory): QuizViewModel
    }

    @Suppress("UNCHECKED_CAST")
    class Provider(
        private val assistedFactory: Factory,
        private var category: QuizCategory
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(category) as T
        }
    }
}