package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.main.MainViewModel
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.LocalDate

@Composable
fun Calendar(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val calendarStatus = mainViewModel.calendarStatus.collectAsState()
    val currentMonth = mainViewModel.currentMonth.collectAsState()
    val currentDay = mainViewModel.currentDay.collectAsState()
    val monthSexList = mainViewModel.monthSexList.collectAsState()

    val calendarState = rememberSelectableCalendarState(
        initialSelectionMode = SelectionMode.Single,
        initialSelection = listOf(LocalDate.now()),
        onSelectionChanged = { it.forEach { day -> mainViewModel.getSexListForDay(day) } },
    )
    val days = calendarState.selectionState.selection
    val month = calendarState.monthState.currentMonth
    if (month != currentMonth.value) mainViewModel.getSexListForMonth(month)
    if (days.isNotEmpty() && days[0] != currentDay.value) mainViewModel.getSexListForDay(days[0])

    Column {
        SelectableCalendar(
            modifier = Modifier.animateContentSize(),
            calendarState = calendarState,
            dayContent = { DayContent(dayState = it, calendarState = calendarState) },
            monthHeader = { MonthHeader(it) },
            weekHeader = { WeekHeader(it) },
            monthContainer = { MonthContainer(it) },
        )
        when (calendarStatus.value) {
            ActionStatus.LOADING -> CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultCalendarPreview() {
    SexStatisticTheme {
        Calendar()
    }
}
