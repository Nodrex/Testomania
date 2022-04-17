package com.earth.testomania.technical.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.core.*
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getQuizList()
    }

    private fun getQuizList() {
        viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase().collectLatest {
                when (it) {
                    is DataState.Loading -> {
                        //TODO show progressbar
                    }
                    is DataState.Success -> {
                        //TODO show tests
                        val result = it.payload
                        println("data => $result")
                    }
                    is DataState.Error -> {
                        //TODO show error
                    }
                }
            }
        }
    }

}