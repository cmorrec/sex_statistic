package com.zzz.sexstatistic.infrastructure.database.model

import java.time.LocalDateTime

data class SexPerson(
    val person: Person,
    val rating: Double,
)

data class Sex(
    val id: String,
    val startDate: LocalDateTime,
    val duration: Int,
    val place: String,
    val sexPersons: List<SexPerson>?,
    val rating: Double?,
)
