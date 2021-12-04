package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState

@Composable
fun DayContent(
    dayState: DayState<DynamicSelectionState>,
    calendarState: CalendarState<DynamicSelectionState>,
) {
    Text(
        text = dayState.date.dayOfMonth.toString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.dp, if (dayState.isCurrentDay) Color.Red else Color.Blue)
            .clickable { calendarState.selectionState.selection = listOf(dayState.date) },
        textAlign = TextAlign.Center,
    )
}