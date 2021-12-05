package com.zzz.sexstatistic.infrastructure.database.model

import java.util.Date

data class SexPerson(
    val person: Person,
    val rating: Double,
)

data class Sex(
    val id: String,
    val startDate: Date,
    val duration: Int,
    val place: String,
    val sexPersons: List<SexPerson>?,
)
