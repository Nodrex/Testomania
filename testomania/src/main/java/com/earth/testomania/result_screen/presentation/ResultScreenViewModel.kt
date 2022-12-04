package com.earth.testomania.result_screen.presentation

import androidx.lifecycle.ViewModel
import com.earth.testomania.result_screen.domain.model.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor() : ViewModel() {

    lateinit var resultData: ResultData

}