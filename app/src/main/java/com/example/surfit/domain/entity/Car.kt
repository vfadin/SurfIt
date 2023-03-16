package com.example.surfit.domain.entity

import android.net.Uri
import com.example.surfit.data.dto.ApiCarsDatabase

data class Car(
    val id: Int = -1,
    val name: String,
    val photoUri: Uri,
    val year: Int,
    val engineCapacity: Double,
    val createdAt: String,
)

fun ApiCarsDatabase.toCar() = Car(
    id = id,
    name = name,
    year = year,
    photoUri = Uri.parse(image),
    engineCapacity = engineCapacity,
    createdAt = createdAt
)