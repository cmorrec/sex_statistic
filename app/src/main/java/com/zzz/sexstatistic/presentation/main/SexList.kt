package com.zzz.sexstatistic.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.Routes
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme

@Composable
fun SexList(
    navController: NavHostController,
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
        if (daySexList.value != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .alpha(if (isLoading) 0.3f else 1f)
            ) {
                items(
                    items = daySexList.value!!,
                    itemContent = {
                        SexCard(
                            sex = it,
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSexListPreview() {
    val navController = rememberNavController()
    SexStatisticTheme {
        SexList(navController)
    }
}
