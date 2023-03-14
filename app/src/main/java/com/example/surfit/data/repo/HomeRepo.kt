package com.example.surfit.data.repo

import com.example.surfit.data.datasource.database.CarsDatabase
import com.example.surfit.data.dto.ApiCarsDatabase
import com.example.surfit.domain.entity.Car
import com.example.surfit.domain.entity.toCar
import com.example.surfit.domain.repo.IHomeRepo

class HomeRepo(
    private val database: CarsDatabase,
) : IHomeRepo {

    override suspend fun getCars(): List<Car> {
        return database.dao().getAll().map { it.toCar() }
    }
}