package com.zzz.sexstatistic.infrastructure.database.model

import java.time.LocalDateTime

data class SexPerson(
    val person: Person,
    val rating: Double,
    val comment: String?,
    val isVisibleComment: Boolean?,
    val wasPrelude: Boolean?,
    val sexualArousal: Int?,
    val getOral: Boolean?,
    val giveOral: Boolean?,
    val havePain: Boolean?,
    val haveOrgasms: Boolean?,
    val orgasmsCount: Int?,
)

data class Sex(
    val id: String,
    val startDate: LocalDateTime,
    val duration: Int?,
    val place: String?,
    val sexPersons: List<SexPerson>,
    val rating: Double?,
)
