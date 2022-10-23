package com.earth.testomania.technical.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.DataState
import com.earth.testomania.common.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.model.QuizCategory
import com.earth.testomania.technical.domain.model.SelectedAnswer
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper
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
    @Assisted var category: QuizCategory,
    private val getQuizListUseCase: GetQuizListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var getQuizListJob: Job? = null

    private val _data = mutableStateListOf<TechQuizItemWrapper>()
    val data: List<TechQuizItemWrapper> = _data

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

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
                    is DataState.Loading -> _loading.value = true
                    is DataState.Success -> it.payload?.apply {
                        _data.addAll(this)
                    }
                    is DataState.Error -> _error.emit(R.string.error_load)
                }
            }
        }
    }

    fun saveAnswer(techQuizItemWrapper: TechQuizItemWrapper, selectedAnswerKey: String) {
        saveQuizPoint(techQuizItemWrapper, selectedAnswerKey)
        val index = _data.indexOf(techQuizItemWrapper)
        val newItem = _data.removeAt(index).let {
            it.copy(
                quiz = it.quiz,
                selectedAnswers = it.selectedAnswers.apply {
                    add(
                        SelectedAnswer(
                            selectedAnswerKey,
                            true
                        )
                    )
                }
            )
        }
        _data.add(index, newItem)
    }

    private fun saveQuizPoint(techQuizItemWrapper: TechQuizItemWrapper, selectedAnswerKey: String) {
        if (techQuizItemWrapper.quiz.hasMultiAnswer) return
        if (isCorrectAnswer(techQuizItemWrapper, selectedAnswerKey)) techQuizItemWrapper.point = 1
    }

    fun isCorrectAnswer(
        techQuizItemWrapper: TechQuizItemWrapper,
        possibleAnswerKey: String
    ) = techQuizItemWrapper.quiz.correctAnswers[possibleAnswerKey] ?: false

    fun wasAlreadyAnswered(techQuizItemWrapper: TechQuizItemWrapper, possibleAnswerKey: String) =
        techQuizItemWrapper.selectedAnswers.find { it.selectedKey == possibleAnswerKey } != null ||
                (techQuizItemWrapper.selectedAnswers.isNotEmpty() &&
                        !techQuizItemWrapper.quiz.hasMultiAnswer &&
                        isCorrectAnswer(
                            techQuizItemWrapper,
                            possibleAnswerKey
                        ))

    fun enableAnswerSelection(techQuizItemWrapper: TechQuizItemWrapper) =
        if (techQuizItemWrapper.quiz.hasMultiAnswer) !techQuizItemWrapper.multiSelectionWasDone.value else techQuizItemWrapper.selectedAnswers.isEmpty()

    fun multiSelectionWasDone(techQuizItemWrapper: TechQuizItemWrapper) {
        saveMultiSelectQuizPoint(techQuizItemWrapper)
        techQuizItemWrapper.multiSelectionWasDone.value = true
    }

    private fun saveMultiSelectQuizPoint(techQuizItemWrapper: TechQuizItemWrapper) {
        if (!techQuizItemWrapper.quiz.hasMultiAnswer) return
        techQuizItemWrapper.selectedAnswers.forEach {
            if (!isCorrectAnswer(techQuizItemWrapper, it.selectedKey)) return
        }
        techQuizItemWrapper.point = 1
    }

    fun getQuizResult() = data.sumOf {
        it.point
    }

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