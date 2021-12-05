package com.zzz.sexstatistic.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.time.YearMonth
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

val person1 = Person(id = "1", gender = Gender.MALE, nickname = "cmorrec", sexList = null)
val person2 = Person(id = "2", gender = Gender.FEMALE, nickname = "vrhaena", sexList = null)
val sexList = listOf(
    Sex(
        id = "1",
        duration = 45,
        place = "Кровать",
        startDate = Date(121, 11, 4, 18, 45),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.6),
            SexPerson(person = person2, rating = 7.8),
        ),
    ),
    Sex(
        id = "2",
        duration = 60,
        place = "Крыша",
        startDate = Date(121, 11, 4, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.3),
            SexPerson(person = person2, rating = 8.5),
        ),
    ),
    Sex(
        id = "3",
        duration = 75,
        place = "Стол",
        startDate = Date(121, 11, 7, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 10.0),
            SexPerson(person = person2, rating = 9.5),
        ),
    ),
)

@HiltViewModel
class MainViewModel @Inject constructor(
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

    private val _monthSexList = MutableStateFlow<List<Sex>?>(null)
    val monthSexList: StateFlow<List<Sex>?>
        get() = _monthSexList

    private val _daySexList = MutableStateFlow<List<Sex>?>(null)
    val daySexList: StateFlow<List<Sex>?>
        get() = _daySexList

    fun getSexListForMonth(month: YearMonth) {
        _currentMonth.value = month
        viewModelScope.launch {
            _calendarStatus.value = ActionStatus.LOADING
            launch {
                delay(1500)
                _monthSexList.value = sexList
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
                _daySexList.value = sexList.filter { sex -> day.isEqual(sex.startDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()) }
                _dayStatus.value = ActionStatus.SUCCESS
            }
        }
    }
}