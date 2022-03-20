package com.zzz.sexstatistic.domain.sex

import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus
import java.time.YearMonth

class GetSexListForMonth(
    private val sexRepo: SexRepo,
) {
    suspend operator fun invoke(month: YearMonth): Pair<List<Sex>, ActionStatus> {
        val from = month.atDay(1)
        val to = month.atEndOfMonth()
        val result = sexRepo.getSexListForInterval(from = from, to = to)
        return Pair(result, ActionStatus.SUCCESS)
    }
}
