package com.zzz.sexstatistic.domain.auth

import com.zzz.sexstatistic.domain.repo.AuthRepo
import com.zzz.sexstatistic.infrastructure.network.model.AuthRequest
import com.zzz.sexstatistic.presentation.ActionStatus

class SignIn(
    private val authRepo: AuthRepo
) {

    suspend operator fun invoke(login: String, password: String): ActionStatus {
        val request = AuthRequest(login = login, password = password)
        val response = authRepo.signIn(request)
        authRepo.saveAuthResult(response)
        return ActionStatus.SUCCESS
    }
}
