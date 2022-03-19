package com.zzz.sexstatistic.presentation.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme

@Composable
fun SexList(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val dayStatus = mainViewModel.dayStatus.collectAsState()
    val currentDay = mainViewModel.currentDay.collectAsState()
    print("dayStatus" + dayStatus.value)

    when (dayStatus.value) {
        ActionStatus.LOADING -> Text("Day ${currentDay.value?.dayOfMonth} loading")
        ActionStatus.SUCCESS -> Text("Day ${currentDay.value?.dayOfMonth} success")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSexListPreview() {
    SexStatisticTheme {
        SexList()
    }
}
