package com.zzz.sexstatistic.presentation.sexEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.zzz.sexstatistic.domain.sex.SexUseCases
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
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

    fun getSexById(sexId: String?) {
        if (sexId == null) {
            _currentSex.value = Sex(
                id = NanoIdUtils.randomNanoId(),
                startDate = getCurrentLocalDateTime(),
                duration = null,
                place = null,
                sexPersons = listOf(),
                rating = null,
            )
            _getSexStatus.value = ActionStatus.SUCCESS
        } else {
            viewModelScope.launch {
                _getSexStatus.value = ActionStatus.LOADING
                val result = sexUseCases.getSexById(sexId)
                _currentSex.value = result.first
                _getSexStatus.value = result.second
            }
        }
    }

    fun saveSex(sex: Sex, navHostController: NavHostController) {
        viewModelScope.launch {
            _saveSexStatus.value = ActionStatus.LOADING
            val result = sexUseCases.saveSex(sex)
            _saveSexStatus.value = result.second
            if (result.second == ActionStatus.SUCCESS) {
                navHostController.navigate(Routes.MAIN)
            }
        }
    }

    private fun getCurrentLocalDateTime(): LocalDateTime {
        return LocalDateTime.now().minusHours(1)
    }
}
