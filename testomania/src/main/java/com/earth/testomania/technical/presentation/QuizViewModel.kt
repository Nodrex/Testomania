package com.earth.testomania.technical.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.common.DataState
import com.earth.testomania.common.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.model.SelectedAnswer
import com.earth.testomania.technical.domain.model.TechQuizItemWrapper
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var getQuizListJob: Job? = null

    private val _data = mutableStateListOf<TechQuizItemWrapper>()
    val data: List<TechQuizItemWrapper> = _data

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _loading.asStateFlow()

    init {
        getQuizList()
    }

    private fun getQuizList() {
        getQuizListJob?.cancel()
        getQuizListJob = viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase().catch {
                ensureActive()
                _error.emit(R.string.error_generic)
            }.collectLatest {
                ensureActive()
                when (it) {
                    is DataState.Loading -> _loading.value = true
                    is DataState.Success -> it.payload?.apply {
                        _data.addAll(this)
                    }
                    is DataState.Error -> {
                        _error.emit(R.string.error_load)
                        _loading.value = false
                    }
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

}