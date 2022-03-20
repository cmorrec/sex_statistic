package com.zzz.sexstatistic.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.zzz.sexstatistic.domain.auth.AuthUseCases
import com.zzz.sexstatistic.presentation.ActionStatus
import com.zzz.sexstatistic.presentation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {
	private val _status = MutableStateFlow(ActionStatus.INIT)
	val status: StateFlow<ActionStatus>
		get() = _status

	fun signIn(login: String, password: String, navController: NavHostController) {
		viewModelScope.launch {
			_status.value = ActionStatus.LOADING
			val res = authUseCases.signIn(
				login = login,
				password = password,
			)
			_status.value = res
            if (res == ActionStatus.SUCCESS) {
                navController.navigate(Routes.MAIN) {
	                navController.backQueue.clear()
                }
            }
		}
	}
}
