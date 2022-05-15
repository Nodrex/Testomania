package com.earth.testomania.skills.presentation.skillz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earth.testomania.domain.models.GeneralMathematicalSkillsTest
import com.earth.testomania.skills.domain.GetAllSkillsTestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillzTestsViewModel @Inject constructor(
    private val getAllSkillsTestsUseCase: GetAllSkillsTestsUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val state = MutableStateFlow(emptyList<GeneralMathematicalSkillsTest>())


    init {
        viewModelScope.launch(ioDispatcher) {
            state.tryEmit(getAllSkillsTestsUseCase.execute())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}