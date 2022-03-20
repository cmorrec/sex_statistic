package com.zzz.sexstatistic.infrastructure.database.model

object Gender {
    const val MALE = "male"
    const val FEMALE = "female"
}

data class Person(
    val id: String,
    val nickname: String,
    val gender: String,
    val sexList: List<Sex>?,
)
