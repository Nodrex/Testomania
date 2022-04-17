package com.earth.testomania.driving_licence.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.domain.use_case.GetDrivingLicenceQuestions
import com.earth.testomania.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrivingLicenceViewModel @Inject constructor(
    private val dao: DrivingLicenceDao,
    private val getDrivingLicenceQuestions: GetDrivingLicenceQuestions
) : ViewModel() {

    fun startNewTest() {
        viewModelScope.launch {
            // for testing purposes
            dao.getSingleQuestions(1)
            delay(3000)
            getDrivingLicenceQuestions.invoke().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("TAG", "startNewTest: ${result.data?.first()}")
                    }
                    is Resource.Loading -> {
                        Log.d("TAG", "startNewTest: loading/&size ${result.data?.size}")
                    }
                    is Resource.Error -> {
                        Log.d("TAG", "startNewTest: Error: ${result.message}")
                    }
                }
            }
        }
    }
}