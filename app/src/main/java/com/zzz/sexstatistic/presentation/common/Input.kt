package com.zzz.sexstatistic.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Input(
  value: String,
  onChange: (String) -> Unit,
  isPassword: Boolean = false,
  label: @Composable (() -> Unit)? = null,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onChange,
    label = label,
    visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
  )
}
