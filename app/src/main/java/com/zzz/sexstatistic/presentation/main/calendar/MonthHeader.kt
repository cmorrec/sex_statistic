package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import io.github.boguszpawlowski.composecalendar.header.MonthState
import java.time.YearMonth

@Composable
fun MonthHeader(monthState: MonthState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        IconButton(onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = "Prev",
            )
        }
        Text(getNameOfMonth(monthState.currentMonth), style = MaterialTheme.typography.h4)
        Text(monthState.currentMonth.year.toString(), style = MaterialTheme.typography.h4)
        IconButton(onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }) {
            Image(
                imageVector = Icons.Default.ArrowForward,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = "Next",
            )
        }
    }
}

private fun getNameOfMonth(month: YearMonth): String {
    return when (month.month.value) {
        1 -> "Январь"
        2 -> "Февраль"
        3 -> "Март"
        4 -> "Апрель"
        5 -> "Май"
        6 -> "Июнь"
        7 -> "Июль"
        8 -> "Август"
        9 -> "Сентябрь"
        10 -> "Октябрь"
        11 -> "Ноябрь"
        12 -> "Декабрь"
        else -> ""
    }
}
