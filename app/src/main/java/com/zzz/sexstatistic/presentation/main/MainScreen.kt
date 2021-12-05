package com.zzz.sexstatistic.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.zzz.sexstatistic.presentation.main.calendar.Calendar

@Composable
fun CalendarScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Calendar()
        SexList()
    }
}
