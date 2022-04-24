package com.earth.testomania.technical.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.core.DataState
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var getQuizListJob: Job? = null

    init {
        getQuizList()
    }

    private fun getQuizList() {
        getQuizListJob?.cancel()
        getQuizListJob = viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase().catch {
                if (isActive) {
                    //TODO update ui accordingly
                }
            }.collectLatest {
                when (it) {
                    is DataState.Loading -> {
                        if (isActive) {
                            //TODO show progressbar
                        }
                    }
                    is DataState.Success -> {
                        if (isActive) {
                            //TODO show tests
                            val result = it.payload
                            println("data => $result")
                        }
                    }
                    is DataState.Error -> {
                        if (isActive) {
                            //TODO show error
                        }
                    }
                }
            }
        }
    }

}