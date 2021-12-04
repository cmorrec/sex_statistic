package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.DayOfWeek

@Composable
fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        daysOfWeek.forEach { day -> Text(getNameOfDayOfWeek(day)) }
    }
}

private fun getNameOfDayOfWeek(day: DayOfWeek): String {
    return when (day.value) {
        1 -> "Пн"
        2 -> "Вт"
        3 -> "Ср"
        4 -> "Чт"
        5 -> "Пт"
        6 -> "Сб"
        7 -> "Вс"
        else -> ""
    }
}
