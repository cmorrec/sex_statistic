package com.zzz.sexstatistic.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.sexstatistic.domain.sex.SexUseCases
import com.zzz.sexstatistic.infrastructure.database.model.Gender
import com.zzz.sexstatistic.infrastructure.database.model.Person
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.infrastructure.database.model.SexPerson
import com.zzz.sexstatistic.presentation.ActionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val sexUseCases: SexUseCases,
): ViewModel() {

    private val _calendarStatus = MutableStateFlow(ActionStatus.INIT)
    val calendarStatus: StateFlow<ActionStatus>
        get() = _calendarStatus

    private val _dayStatus = MutableStateFlow(ActionStatus.INIT)
    val dayStatus: StateFlow<ActionStatus>
        get() = _dayStatus

    private val _currentMonth = MutableStateFlow<YearMonth?>(null)
    val currentMonth: StateFlow<YearMonth?>
        get() = _currentMonth

    private val _currentDay = MutableStateFlow<LocalDate?>(null)
    val currentDay: StateFlow<LocalDate?>
        get() = _currentDay

    private val _monthSexList = MutableStateFlow<List<Sex>?>(null)
    val monthSexList: StateFlow<List<Sex>?>
        get() = _monthSexList

    private val _daySexList = MutableStateFlow<List<Sex>?>(null)
    val daySexList: StateFlow<List<Sex>?>
        get() = _daySexList

    fun getSexListForMonth(month: YearMonth) {
        viewModelScope.launch {
            _currentMonth.value = month
            _calendarStatus.value = ActionStatus.LOADING
            val result = sexUseCases.getSexListForMonth(month)
            _monthSexList.value = result.first
            _calendarStatus.value = result.second
        }
    }

    fun getSexListForDay(day: LocalDate) {
        viewModelScope.launch {
            _currentDay.value = day
            _dayStatus.value = ActionStatus.LOADING
            val result = sexUseCases.getSexListForDay(day)
            _daySexList.value = result.first
            _dayStatus.value = result.second
        }
    }
}
