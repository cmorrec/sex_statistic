package com.zzz.sexstatistic.infrastructure

import android.content.SharedPreferences
import com.zzz.sexstatistic.domain.repo.SexRepo
import com.zzz.sexstatistic.infrastructure.database.model.Gender
import com.zzz.sexstatistic.infrastructure.database.model.Person
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.infrastructure.database.model.SexPerson
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime

val person1 = Person(id = "1", gender = Gender.MALE, nickname = "cmorrec", sexList = null)
val person2 = Person(id = "2", gender = Gender.FEMALE, nickname = "vrhaena", sexList = null)
val sexList = listOf(
    Sex(
        id = "1",
        duration = 45,
        place = "Кровать",
        startDate = LocalDateTime.of(2022, 3, 19, 18, 45),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.6),
            SexPerson(person = person2, rating = 7.8),
        ),
        rating = 8.2,
    ),
    Sex(
        id = "2",
        duration = 60,
        place = "Крыша",
        startDate = LocalDateTime.of(2022, 3, 19, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 8.3),
            SexPerson(person = person2, rating = 8.5),
        ),
        rating = 8.4,
    ),
    Sex(
        id = "3",
        duration = 75,
        place = "Стол",
        startDate = LocalDateTime.of(2022, 3, 20, 21, 30),
        sexPersons = listOf(
            SexPerson(person = person1, rating = 10.0),
            SexPerson(person = person2, rating = 9.5),
        ),
        rating = 9.75,
    ),
)

class SexRepoImpl(
    private val authSharedPreferences: SharedPreferences,
): SexRepo {

    override suspend fun getSexListForInterval(from: LocalDate, to: LocalDate): List<Sex> {
        delay(700)
        return sexList.filter {
            it.startDate.isAfter(from.atStartOfDay())
                && it.startDate.isBefore(to.atTime(23, 59))
        }
    }

    override suspend fun getSexById(sexId: String): Sex? {
        delay(700)
        return sexList.find { it.id == sexId }
    }

}
