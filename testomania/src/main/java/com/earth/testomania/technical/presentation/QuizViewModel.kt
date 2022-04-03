package com.earth.testomania.technical.presentation

import android.service.autofill.Dataset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.core.*
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.technical.domain.use_case.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
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

    private fun getQuizList(){
        viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            getQuizListUseCase().collectLatest {
                when(it){
                    is DataState.Loading -> {
                        when(it.metaData){
                            is SuccessData -> {

                            }
                            is LoadingData -> {

                            }
                            is ErrorData -> {
                                
                            }
                            null -> {

                            }
                        }
                    }
                    is DataState.Success -> {

                    }
                    is DataState.Error -> {

                    }
                    /*is DataState.Unusual -> {

                    }*/
                }
                println(it)
            }
        }
    }

}