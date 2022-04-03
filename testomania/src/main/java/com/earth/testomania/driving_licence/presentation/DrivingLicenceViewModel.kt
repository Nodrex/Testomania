package com.earth.testomania.driving_licence.presentation

import androidx.lifecycle.ViewModel
import com.earth.testomania.driving_licence.domain.use_case.GetDrivingLicenceQuestions
import com.earth.testomania.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DrivingLicenceViewModel @Inject constructor(
    private val getDrivingLicenceQuestions: GetDrivingLicenceQuestions
) : ViewModel() {

    init {
        startNewTest()
    }

    fun startNewTest() {

        getDrivingLicenceQuestions.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {

                }
                is Resource.Loading -> {

                }
            }
        }
    }
}