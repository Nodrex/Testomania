package com.earth.testomania.presentation.skillz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.domain.GetAllSkillsTestsUseCase
import com.earth.testomania.domain.models.GeneralMathematicalSkillsTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillzTestsViewModel @Inject constructor(
    private val getAllSkillsTestsUseCase: GetAllSkillsTestsUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var state by mutableStateOf(emptyList<GeneralMathematicalSkillsTest>())
        private set

    init {
        viewModelScope.launch(ioDispatcher) {
            state = getAllSkillsTestsUseCase.execute()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}