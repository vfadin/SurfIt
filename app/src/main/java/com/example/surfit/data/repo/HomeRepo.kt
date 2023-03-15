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

    override suspend fun getCar(id: Int): Car {
        TODO("Not yet implemented")
    }

    override suspend fun insertCar(car: Car) {
        car.apply {
            database.dao().insert(ApiCarsDatabase(0, name, year, engineCapacity, createdAt))
        }
    }
}