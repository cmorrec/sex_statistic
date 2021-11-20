package com.zzz.sexstatistic.view.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.sexstatistic.ui.theme.SexStatisticTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

class AuthActivity : ComponentActivity() {
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
                    SignIn()
                }
            }
        }
    }
}

@Composable
fun SignIn() {
    val (login, setLogin) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    Text(text = "Hello $login!")
    OutlinedTextField(
        value = login,
        onValueChange = { setLogin(it) },
        label = { Text(text = "Логин") },
    )
    OutlinedTextField(
        value = password,
        onValueChange = { setPassword(it) },
        label = { Text(text = "Пароль") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )

    Button(
        onClick = { /* TODO */ },
        modifier = Modifier.padding(top = 8.dp),
    ) {
        Text(text = "Жмакай")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SexStatisticTheme {
        SignIn()
    }
}
