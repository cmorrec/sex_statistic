package com.zzz.sexstatistic.presentation.calendar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun Calendar(
    calendarViewModel: CalendarViewModel = hiltViewModel(),
) {
    val calendarStatus = calendarViewModel.calendarStatus.collectAsState()
    val currentMonth = calendarViewModel.currentMonth.collectAsState()
    val currentDay = calendarViewModel.currentDay.collectAsState()

    val calendarState = rememberSelectableCalendarState(
        initialSelectionMode = SelectionMode.Single,
        initialSelection = listOf(LocalDate.now()),
        onSelectionChanged = { it.forEach { day -> calendarViewModel.getSexListForDay(day) } },
    )
    val days = calendarState.selectionState.selection
    val month = calendarState.monthState.currentMonth
    if (month != currentMonth.value) calendarViewModel.getSexListForMonth(month)
    if (days.isNotEmpty() && days[0] != currentDay.value) calendarViewModel.getSexListForDay(days[0])

    when (calendarStatus.value) {
        ActionStatus.LOADING -> Text("Month ${month.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} loading")
        ActionStatus.SUCCESS -> SelectableCalendar(calendarState = calendarState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultCalendarPreview() {
    SexStatisticTheme {
        Calendar()
    }
}
