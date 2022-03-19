package com.zzz.sexstatistic.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    val daySexList = mainViewModel.daySexList.collectAsState()
    val isLoading = dayStatus.value == ActionStatus.LOADING

    Box {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Column(modifier = Modifier.alpha(if (isLoading) 0.3f else 1f)) {
            if (daySexList.value != null) {
                daySexList.value!!.forEach { SexCard(it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSexListPreview() {
    SexStatisticTheme {
        SexList()
    }
}
