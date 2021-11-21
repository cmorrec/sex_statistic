package com.zzz.sexstatistic.infrastructure.network.model

data class AuthResponse(
    val authToken: String, val userId: String, val username: String,
)
