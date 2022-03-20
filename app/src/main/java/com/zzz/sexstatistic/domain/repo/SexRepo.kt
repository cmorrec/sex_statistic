package com.zzz.sexstatistic.domain.repo

import com.zzz.sexstatistic.infrastructure.database.model.Sex
import java.time.LocalDate

interface SexRepo {

    suspend fun getSexListForInterval(from: LocalDate, to: LocalDate): List<Sex>

    suspend fun getSexById(sexId: String): Sex?

}
