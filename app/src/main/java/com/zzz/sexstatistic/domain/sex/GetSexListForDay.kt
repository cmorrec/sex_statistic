package com.zzz.sexstatistic.domain.sex

import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus
import java.time.LocalDate

class GetSexListForDay(
    private val sexRepo: SexRepo,
) {
    suspend operator fun invoke(date: LocalDate): Pair<List<Sex>, ActionStatus> {
        val result = sexRepo.getSexListForInterval(from = date, to = date)
        return Pair(result, ActionStatus.SUCCESS)
    }
}