package com.zzz.sexstatistic.view.auth

import android.os.SystemClock.sleep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.sexstatistic.common.ActionStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AuthViewModel : ViewModel() {
    private val _status = MutableStateFlow(ActionStatus.INIT)
    val status: StateFlow<ActionStatus>
        get() = _status

    fun signIn(login: String, password: String) {
        viewModelScope.launch {
            _status.value = ActionStatus.LOADING
            launch {
                delay(1000)
                _status.value = ActionStatus.SUCCESS
            }
        }
    }
}