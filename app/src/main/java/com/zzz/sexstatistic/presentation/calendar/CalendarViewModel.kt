package com.zzz.sexstatistic.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.sexstatistic.presentation.ActionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
//    private val authUseCases: AuthUseCases,
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

//    private val _monthSexList = MutableStateFlow()
//    val monthSexList: StateFlow<>
//        get() = _monthSexList

//    private val _daySexList = MutableStateFlow()
//    val daySexList: StateFlow<>
//        get() = _daySexList

    fun getSexListForMonth(month: YearMonth) {
        _currentMonth.value = month
        viewModelScope.launch {
            _calendarStatus.value = ActionStatus.LOADING
            launch {
//                val res = authUseCases.signIn(
//                    login = login,
//                    password = password,
//                )
                delay(1500)
                _calendarStatus.value = ActionStatus.SUCCESS
            }
        }
    }

    fun getSexListForDay(day: LocalDate) {
        _currentDay.value = day
        viewModelScope.launch {
            _dayStatus.value = ActionStatus.LOADING
            launch {
                delay(3000)
                _dayStatus.value = ActionStatus.SUCCESS
            }
        }
    }
}