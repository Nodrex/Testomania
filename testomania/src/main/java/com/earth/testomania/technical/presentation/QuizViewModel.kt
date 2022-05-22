package com.earth.testomania.technical.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.R
import com.earth.testomania.core.DataState
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.model.TechQuiz
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

    private val _data = MutableStateFlow<List<TechQuiz>>(emptyList())
    val data = _data.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _loading.asStateFlow()

    private val _showCorrectAnswer = MutableStateFlow(false)
    val showCorrectAnswer = _showCorrectAnswer.asStateFlow()

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
                        _data.value = this
                    }
                    is DataState.Error -> _error.emit(R.string.error_load)
                }
            }
        }
    }

    fun showCorrectAnswer(){
        _showCorrectAnswer.value = true
        println("showCorrectAnswer (${_showCorrectAnswer.value})")
    }

}