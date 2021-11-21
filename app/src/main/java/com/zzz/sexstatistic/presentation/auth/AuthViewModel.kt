package com.zzz.sexstatistic.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.sexstatistic.domain.AuthUseCases
import com.zzz.sexstatistic.presentation.ActionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
): ViewModel() {
    private val _status = MutableStateFlow(ActionStatus.INIT)
    val status: StateFlow<ActionStatus>
        get() = _status

    fun signIn(login: String, password: String) {
        viewModelScope.launch {
            _status.value = ActionStatus.LOADING
            launch {
                val res = authUseCases.signIn(
                    login = login,
                    password = password,
                )
                _status.value = res
            }
        }
    }
}
