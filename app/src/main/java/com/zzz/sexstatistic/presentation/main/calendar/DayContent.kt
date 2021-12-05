package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.YearMonth

@Composable
fun DayContent(
    dayState: DayState<DynamicSelectionState>,
    calendarState: CalendarState<DynamicSelectionState>,
) {
    val selection = calendarState.selectionState.selection
    val date = dayState.date
    val isSelected = selection.isNotEmpty() && selection[0].isEqual(date)
    val dateMonth = YearMonth.from(date)
    val isCurrentMonth = calendarState.monthState.currentMonth == dateMonth

    Text(
        text = date.dayOfMonth.toString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                if (isCurrentMonth) 1.dp else 0.dp,
                if (dayState.isCurrentDay) Color.Red else Color.Gray,
                CircleShape,
            )
            .padding(4.dp)
            .clickable(enabled = !isSelected) {
                calendarState.selectionState.selection = listOf(date)
                // TODO animate change of month
                if (!isCurrentMonth) calendarState.monthState.currentMonth = dateMonth
            },
        textAlign = TextAlign.Center,
        color = if (isSelected) Color.Cyan else if (isCurrentMonth) Color.Black else Color.LightGray,
    )
}
