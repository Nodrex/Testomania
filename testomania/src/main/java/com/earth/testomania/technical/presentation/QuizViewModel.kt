package com.earth.testomania.technical.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.core.DataState
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.core.log
import com.earth.testomania.technical.domain.model.TechQuizWrapper
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

    private val _data = mutableStateListOf<TechQuizWrapper>()
    val data: List<TechQuizWrapper> = _data

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
                    is DataState.Error -> _error.emit(R.string.error_load)
                }
            }
        }
    }

    fun saveAnswer(techQuizWrapper: TechQuizWrapper, selectedAnswerKey: String) {
        val index = _data.indexOf(techQuizWrapper)
        _data[index] = _data[index].let {
            log("old quiz ${it.hashCode()}")
            it.copy(
                quiz = it.quiz,
                userSelectedAnswers = it.userSelectedAnswers.apply { add(selectedAnswerKey) }
            )
        }
        log("new obj ${_data[index].hashCode()}")
        println("=> selected answer was added $selectedAnswerKey")
    }

    fun isCorrectAnswer(
        techQuizWrapper: TechQuizWrapper,
        possibleAnswerKey: String
    ) = techQuizWrapper.quiz.correctAnswers[possibleAnswerKey] ?: false

    fun wasSelected(techQuizWrapper: TechQuizWrapper, possibleAnswerKey: String) =
        //TODO jer dasamatebelia logika multiselectioze
        techQuizWrapper.userSelectedAnswers.contains(possibleAnswerKey) ||
                (techQuizWrapper.userSelectedAnswers.isNotEmpty() &&
                        !techQuizWrapper.quiz.hasMultiAnswer &&
                        isCorrectAnswer(
                            techQuizWrapper,
                            possibleAnswerKey
                        ))

}