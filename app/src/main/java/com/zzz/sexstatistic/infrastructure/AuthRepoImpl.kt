package com.zzz.sexstatistic.infrastructure

import android.content.SharedPreferences
import com.zzz.sexstatistic.domain.repo.AuthRepo
import com.zzz.sexstatistic.infrastructure.database.model.UserShortInfo
import com.zzz.sexstatistic.infrastructure.network.model.AuthRequest
import com.zzz.sexstatistic.infrastructure.network.model.AuthResponse
import kotlinx.coroutines.delay

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
            putString(SharedPreferencesKeys.AUTH_TOKEN, result.authToken)
            putString(SharedPreferencesKeys.USER_ID, result.userId)
            putString(SharedPreferencesKeys.USERNAME, result.username)
            apply()
        }
    }

    override suspend fun getToken(): String? {
        return authSharedPreferences.getString(SharedPreferencesKeys.AUTH_TOKEN, null)
    }

    override suspend fun getUser(): UserShortInfo? {
        val userId = authSharedPreferences.getString(SharedPreferencesKeys.USER_ID, null)
        val username = authSharedPreferences.getString(SharedPreferencesKeys.USERNAME, null)
        if (userId == null || username == null) return null
        return UserShortInfo(userId = userId, username = username)
    }
}
