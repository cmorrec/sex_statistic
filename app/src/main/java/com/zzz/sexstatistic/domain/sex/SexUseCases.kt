package com.zzz.sexstatistic.domain.sex

data class SexUseCases (
    val getSexListForMonth: GetSexListForMonth,
    val getSexListForDay: GetSexListForDay,
    val getSexById: GetSexById,
    val saveSex: SaveSex,
)
