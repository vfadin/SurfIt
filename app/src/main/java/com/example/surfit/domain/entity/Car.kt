package com.example.surfit.domain.entity

import com.example.surfit.data.dto.ApiCarsDatabase

data class Car(
    val name: String,
    val photo: String,
    val year: Int,
    val engineCapacity: Double,
    val createdAt: String
)

fun ApiCarsDatabase.toCar() = Car(
    name = name,
    year = year,
    photo = "",
    engineCapacity = engineCapacity,
    createdAt = createdAt
)