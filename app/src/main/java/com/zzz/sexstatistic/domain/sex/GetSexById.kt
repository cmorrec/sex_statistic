package com.zzz.sexstatistic.domain.sex

import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus

class GetSexById(
    private val sexRepo: SexRepo,
) {
    suspend operator fun invoke(sexId: String): Pair<Sex?, ActionStatus> {
        val result = sexRepo.getSexById(sexId = sexId)
        return Pair(result, ActionStatus.SUCCESS)
    }
}
