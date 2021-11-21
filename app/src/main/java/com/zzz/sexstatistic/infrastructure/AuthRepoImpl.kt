package com.zzz.sexstatistic.infrastructure

import android.content.SharedPreferences
import com.zzz.sexstatistic.domain.repo.AuthRepo
import com.zzz.sexstatistic.infrastructure.database.model.UserShortInfo
import com.zzz.sexstatistic.infrastructure.network.model.AuthRequest
import com.zzz.sexstatistic.infrastructure.network.model.AuthResponse
import kotlinx.coroutines.delay

const val AUTH_TOKEN = "authToken"
const val USER_ID = "userId"
const val USERNAME = "username"

class AuthRepoImpl(
    private val authSharedPreferences: SharedPreferences,
): AuthRepo {
    override suspend fun signIn(req: AuthRequest): AuthResponse {
        // TODO("Not yet implemented")
        delay(700)
        return AuthResponse("authToken", "userId", "username")
    }

    override suspend fun signUp(req: AuthRequest): AuthResponse {
        // TODO("Not yet implemented")
        return signIn(req)
    }

    override suspend fun saveAuthResult(result: AuthResponse) {
        with (authSharedPreferences.edit()) {
            putString(AUTH_TOKEN, result.authToken)
            putString(USER_ID, result.userId)
            putString(USERNAME, result.username)
            apply()
        }
    }

    override suspend fun getToken(): String? {
        return authSharedPreferences.getString(AUTH_TOKEN, null)
    }

    override suspend fun getUser(): UserShortInfo? {
        val userId = authSharedPreferences.getString(USER_ID, null)
        val username = authSharedPreferences.getString(USERNAME, null)
        if (userId == null || username == null) return null
        return UserShortInfo(userId = userId, username = username)
    }
}
