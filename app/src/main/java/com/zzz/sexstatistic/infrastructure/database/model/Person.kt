package com.zzz.sexstatistic.infrastructure.database.model

object Gender {
    val MALE = "male"
    val FEMALE = "female"
}

data class Person(
    val id: String,
    val nickname: String,
    val gender: String,
    val sexList: List<Sex>?,
)
