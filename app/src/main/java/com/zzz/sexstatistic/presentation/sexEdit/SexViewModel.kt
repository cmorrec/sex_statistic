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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class SexViewModel @Inject constructor(
  private val sexUseCases: SexUseCases,
) : ViewModel() {

  private val _gettingSexStatus = MutableStateFlow(ActionStatus.INIT)
  val gettingSexStatus: StateFlow<ActionStatus>
    get() = _gettingSexStatus

  private val _savingSexStatus = MutableStateFlow(ActionStatus.INIT)
  val savingSexStatus: StateFlow<ActionStatus>
    get() = _savingSexStatus

  private val _currentSex = MutableStateFlow<Sex?>(null)
  val currentSex: StateFlow<Sex?>
    get() = _currentSex

  private val _currentDate = MutableStateFlow<LocalDate?>(null)
  val currentDate: StateFlow<LocalDate?>
    get() = _currentDate

  private val _currentTime = MutableStateFlow<LocalTime?>(null)
  val currentTime: StateFlow<LocalTime?>
    get() = _currentTime

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
      _gettingSexStatus.value = ActionStatus.SUCCESS
    } else {
      viewModelScope.launch {
        _gettingSexStatus.value = ActionStatus.LOADING
        val result = sexUseCases.getSexById(sexId)
        _currentSex.value = result.first
        saveDate(result.first?.startDate?.toLocalDate())
        saveTime(result.first?.startDate?.toLocalTime())
        _gettingSexStatus.value = result.second
      }
    }
  }

  fun saveDate(newDate: LocalDate?) {
    _currentDate.value = newDate
  }

  fun saveTime(newTime: LocalTime?) {
    _currentTime.value = newTime
  }

  fun saveSex(sex: Sex, navHostController: NavHostController) {
    viewModelScope.launch {
      _savingSexStatus.value = ActionStatus.LOADING
      val result = sexUseCases.saveSex(sex)
      _savingSexStatus.value = result.second
      if (result.second == ActionStatus.SUCCESS) {
        navHostController.navigate(Routes.MAIN)
      }
    }
  }

  private fun getCurrentLocalDateTime(): LocalDateTime {
    return LocalDateTime.now().minusHours(1)
  }
}
