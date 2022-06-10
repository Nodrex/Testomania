package com.earth.testomania.presentation.result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(): ViewModel() {

    val isTestDone = true

}