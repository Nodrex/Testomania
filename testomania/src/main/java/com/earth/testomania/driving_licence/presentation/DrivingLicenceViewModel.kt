package com.earth.testomania.driving_licence.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.core.DataState
import com.earth.testomania.core.coroutines.defaultCoroutineExceptionHandler
import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.domain.use_case.GetDrivingLicenceQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrivingLicenceViewModel @Inject constructor(
    private val dao: DrivingLicenceDao,
    private val getDrivingLicenceQuestions: GetDrivingLicenceQuestionsUseCase,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var getQuestionsJob: Job? = null

    fun startNewTest() {
        getQuestionsJob?.cancel()
        getQuestionsJob = viewModelScope.launch(dispatcher + defaultCoroutineExceptionHandler) {
            // for testing purposes
            dao.getSingleQuestions(1)
            delay(3000)
            getDrivingLicenceQuestions.invoke().collectLatest { result ->
                when (result) {
                    is DataState.Success -> {
                        Log.d("TAG", "startNewTest: ${result.payload?.first()}")
                    }
                    is DataState.Loading -> {
                        Log.d("TAG", "startNewTest: loading/&size ${result.payload?.size}")
                    }
                    is DataState.Error -> {
                        Log.d("TAG", "startNewTest: Error: ${result.metaData}")
                    }
                }
            }
        }
    }
}