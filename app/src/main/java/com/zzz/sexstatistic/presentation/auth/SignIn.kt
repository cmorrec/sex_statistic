package com.zzz.sexstatistic.presentation.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.common.ActionButton
import com.zzz.sexstatistic.presentation.common.Input

@Composable
fun SignIn(
  navController: NavHostController,
  authViewModel: AuthViewModel = hiltViewModel(),
) {
  val (login, setLogin) = rememberSaveable { mutableStateOf("") }
  val (password, setPassword) = rememberSaveable { mutableStateOf("") }
  val status by authViewModel.status.collectAsState()
  when (status) {
    ActionStatus.LOADING -> Text("Loading")
    ActionStatus.SUCCESS -> Text("Success")
  }
  Text("Hello $login!")
  Input(
    value = login,
    onChange = { setLogin(it) },
    label = { Text("Логин") },
  )
  Input(
    value = password,
    onChange = { setPassword(it) },
    label = { Text("Пароль") },
    isPassword = false,
  )

  ActionButton(
    text = "Жмакай",
    modifier = Modifier.padding(top = 8.dp),
    enabled = status != ActionStatus.LOADING
  ) {
    authViewModel.signIn(login, password, navController)
  }
}
