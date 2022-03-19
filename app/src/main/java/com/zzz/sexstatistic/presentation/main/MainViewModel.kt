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
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject

val person1 = Person(id = "1", gender = Gender.MALE, nickname = "cmorrec", sexList = null)
val person2 = Person(id = "2", gender = Gender.FEMALE, nickname = "vrhaena", sexList = null)
val sexList = listOf(
    Sex(
        id = "1",
        duration = 45,
        place = "Кровать",
        startDate = LocalDateTime.of(2022, 3, 19, 18, 45),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.6),
            SexPerson(person = person2, rating = 7.8),
        ),
        rating = 8.2,
    ),
    Sex(
        id = "2",
        duration = 60,
        place = "Крыша",
        startDate = LocalDateTime.of(2022, 3, 19, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.3),
            SexPerson(person = person2, rating = 8.5),
        ),
        rating = 8.4,
    ),
    Sex(
        id = "3",
        duration = 75,
        place = "Стол",
        startDate = LocalDateTime.of(2022, 3, 20, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 10.0),
            SexPerson(person = person2, rating = 9.5),
        ),
        rating = 9.75,
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
        viewModelScope.launch {
            _currentMonth.value = month
            _calendarStatus.value = ActionStatus.LOADING
            delay(1500)
            _monthSexList.value = sexList
            _calendarStatus.value = ActionStatus.SUCCESS
        }
    }

    fun getSexListForDay(day: LocalDate) {
        viewModelScope.launch {
            _currentDay.value = day
            _dayStatus.value = ActionStatus.LOADING
            delay(3000)
            _daySexList.value = sexList.filter { sex -> day.isEqual(sex.startDate.toLocalDate()) }
            _dayStatus.value = ActionStatus.SUCCESS
        }
    }
}
