package com.zzz.sexstatistic.di

import android.app.Application
import com.zzz.sexstatistic.domain.auth.AuthUseCases
import com.zzz.sexstatistic.domain.auth.SignIn
import com.zzz.sexstatistic.domain.repo.AuthRepo
import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.domain.sex.*
import com.zzz.sexstatistic.infrastructure.AuthRepoImpl
import com.zzz.sexstatistic.infrastructure.SexRepoImpl
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
    fun provideSexRepo(app: Application): SexRepo {
        val authSharedPreferences = getAuthSharedPreferences(app)
        return SexRepoImpl(
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

    @Provides
    @Singleton
    fun provideSexUseCases(repo: SexRepo): SexUseCases {
        return SexUseCases(
            getSexById = GetSexById(repo),
            getSexListForDay = GetSexListForDay(repo),
            getSexListForMonth = GetSexListForMonth(repo),
            saveSex = SaveSex(repo),
        )
    }
}
