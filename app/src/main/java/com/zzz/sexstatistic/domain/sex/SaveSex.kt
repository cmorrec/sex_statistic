package com.zzz.sexstatistic.domain.sex

import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.presentation.ActionStatus

class SaveSex(
    private val sexRepo: SexRepo,
) {
    suspend operator fun invoke(sex: Sex): Pair<Sex, ActionStatus> {
        val res = sexRepo.saveSex(sex)
        return Pair(res, ActionStatus.SUCCESS)
    }
}
