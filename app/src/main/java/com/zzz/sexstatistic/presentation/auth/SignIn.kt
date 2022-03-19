package com.zzz.sexstatistic.presentation.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zzz.sexstatistic.presentation.ActionStatus

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
    OutlinedTextField(
        value = login,
        onValueChange = { setLogin(it) },
        label = { Text("Логин") },
    )
    OutlinedTextField(
        value = password,
        onValueChange = { setPassword(it) },
        label = { Text("Пароль") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )

    Button(
        onClick = { authViewModel.signIn(login, password, navController) },
        modifier = Modifier.padding(top = 8.dp),
        enabled = status != ActionStatus.LOADING
    ) {
        Text("Жмакай")
    }
}
