package com.zzz.sexstatistic.presentation.main.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun MonthContainer(content: @Composable (PaddingValues) -> Unit) {
    Card(
        content = { content(PaddingValues(4.dp)) },
    )
}
