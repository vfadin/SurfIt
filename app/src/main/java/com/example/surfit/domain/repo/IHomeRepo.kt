package com.example.surfit.domain.repo

import com.example.surfit.domain.entity.Car

interface IHomeRepo {
    suspend fun getCars(): List<Car>
}