package com.zzz.sexstatistic.di

import android.app.Application
import com.zzz.sexstatistic.domain.AuthUseCases
import com.zzz.sexstatistic.domain.SignIn
import com.zzz.sexstatistic.domain.repo.AuthRepo
import com.zzz.sexstatistic.infrastructure.AuthRepoImpl
import com.zzz.sexstatistic.infrastructure.database.getAuthSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepo(app: Application): AuthRepo {
        val authSharedPreferences = getAuthSharedPreferences(app)
        return AuthRepoImpl(
            authSharedPreferences = authSharedPreferences,
        )
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repo: AuthRepo): AuthUseCases {
        return AuthUseCases(
            signIn = SignIn(repo),
        )
    }
}
