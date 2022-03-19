package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    val isLoading = calendarStatus.value == ActionStatus.LOADING

    Box {
        SelectableCalendar(
            modifier = Modifier
                .animateContentSize()
                .alpha(if (isLoading) 0.3f else 1f),
            calendarState = calendarState,
            dayContent = { DayContent(dayState = it, calendarState = calendarState) },
            monthHeader = { MonthHeader(it) },
            weekHeader = { WeekHeader(it) },
            monthContainer = { MonthContainer(it) },
        )
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
