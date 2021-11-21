package com.zzz.sexstatistic.domain.repo

import com.zzz.sexstatistic.infrastructure.database.model.UserShortInfo
import com.zzz.sexstatistic.infrastructure.network.model.AuthResponse
import com.zzz.sexstatistic.infrastructure.network.model.AuthRequest

interface AuthRepo {

    suspend fun signIn(req: AuthRequest): AuthResponse

    suspend fun signUp(req: AuthRequest): AuthResponse

    suspend fun saveAuthResult(result: AuthResponse)

    suspend fun getToken(): String?

    suspend fun getUser(): UserShortInfo?
}
