package com.zzz.sexstatistic.presentation.sexEdit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.common.ActionButton
import com.zzz.sexstatistic.presentation.common.CustomDatePicker
import com.zzz.sexstatistic.presentation.common.CustomTimePicker

@Composable
fun SexEdit(
  sexId: String?,
  navHostController: NavHostController,
  sexViewModel: SexViewModel = hiltViewModel(),
) {
  val currentSex by sexViewModel.currentSex.collectAsState()
  val date by sexViewModel.currentDate.collectAsState()
  val time by sexViewModel.currentTime.collectAsState()
  val gettingSexStatus = sexViewModel.gettingSexStatus.collectAsState()
  val savingSexStatus = sexViewModel.savingSexStatus.collectAsState()
  val isLoading = gettingSexStatus.value == ActionStatus.LOADING || savingSexStatus.value == ActionStatus.LOADING

  LaunchedEffect(sexId) {
    sexViewModel.getSexById(sexId)
  }

  Box {
    if (isLoading) {
      CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    } else {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
      ) {
        Row {
          CustomDatePicker(date) { sexViewModel.saveDate(it) }
          CustomTimePicker(time) { sexViewModel.saveTime(it) }
        }
        ActionButton(
          text = "Save",
          onClick = { sexViewModel.saveSex(currentSex!!, navHostController) },
          enabled = isEnabled(),
        )
      }
    }
  }
}

fun isEnabled(): Boolean {
  return true
}

@Preview(showBackground = false)
@Composable
fun SexEditPreview() {
  val navHostController = rememberNavController()
  SexEdit(sexId = "1", navHostController = navHostController)
}
