package com.zzz.sexstatistic.view.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.sexstatistic.ui.theme.SexStatisticTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.zzz.sexstatistic.common.ActionStatus

class AuthActivity : ComponentActivity() {
    private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SexStatisticTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    SignIn(authViewModel) { login, password -> authViewModel.signIn(login, password) }
                }
            }
        }
    }
}

@Composable
fun SignIn(authViewModel: AuthViewModel, signIn: (login: String, password: String) -> Unit) {
    val (login, setLogin) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
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
        onClick = { signIn(login, password) },
        modifier = Modifier.padding(top = 8.dp),
        enabled = status != ActionStatus.LOADING
    ) {
        Text("Жмакай")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SexStatisticTheme {
//        SignIn { login, password -> Log.e(login, password) }
//    }
//}
