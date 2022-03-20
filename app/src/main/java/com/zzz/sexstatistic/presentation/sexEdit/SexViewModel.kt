package com.zzz.sexstatistic.presentation.sexEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.sexstatistic.domain.sex.SexUseCases
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SexViewModel @Inject constructor(
    private val sexUseCases: SexUseCases,
): ViewModel() {

    private val _getSexStatus = MutableStateFlow(ActionStatus.INIT)
    val getSexStatus: StateFlow<ActionStatus>
        get() = _getSexStatus

    private val _saveSexStatus = MutableStateFlow(ActionStatus.INIT)
    val saveSexStatus: StateFlow<ActionStatus>
        get() = _saveSexStatus

    private val _currentSex = MutableStateFlow<Sex?>(null)
    val currentSex: StateFlow<Sex?>
        get() = _currentSex

    fun getSexById(sexId: String) {
        viewModelScope.launch {
            _getSexStatus.value = ActionStatus.LOADING
            val result = sexUseCases.getSexById(sexId)
            _currentSex.value = result.first
            _getSexStatus.value = result.second
        }
    }
}
