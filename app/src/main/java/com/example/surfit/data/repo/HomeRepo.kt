package com.example.surfit.data.repo

import android.content.Context
import com.example.surfit.data.datasource.database.CarsDatabase
import com.example.surfit.data.dto.ApiAvailableForViewingIds
import com.example.surfit.data.dto.ApiCarsDatabase
import com.example.surfit.domain.entity.Car
import com.example.surfit.domain.entity.toCar
import com.example.surfit.domain.repo.IHomeRepo
import com.example.surfit.utils.Constants.FREE_ADD_ATTEMPTS_COUNT
import com.example.surfit.utils.Constants.FREE_VIEW_ATTEMPTS_COUNT
import com.example.surfit.utils.SharedPreferences

class HomeRepo(
    private val database: CarsDatabase,
    private val context: Context,
) : IHomeRepo {

    private fun isTokenPaid(): Boolean {
        println(SharedPreferences(context).restorePurchaseToken() == "PAID_TOKEN")
        return SharedPreferences(context).restorePurchaseToken() == "PAID_TOKEN"
    }

    override suspend fun getCars(): List<Car> {
        return database.carsDao().getAll().map { it.toCar() }
    }

    override suspend fun getCar(id: Int): Car? {
        database.idsDao().apply {
            val addedCarsCount = SharedPreferences(context).restoreAddAttemptNumber()
            if (sizeOfIds() < FREE_VIEW_ATTEMPTS_COUNT + addedCarsCount || isTokenPaid()) {
                saveId(ApiAvailableForViewingIds(id))
                return database.carsDao().getById(id)?.toCar()
            } else {
                getAllIds().forEach {
                    if (it.id == id) {
                        return database.carsDao().getById(id)?.toCar()
                    }
                }
            }
        }
        return null
    }

    override suspend fun insertCar(car: Car) {
        car.apply {
            val prefs = SharedPreferences(context)
            val attempt = prefs.restoreAddAttemptNumber()
            if (attempt < FREE_ADD_ATTEMPTS_COUNT || isTokenPaid()) {
                database.carsDao().apply {
                    val newId = insert(ApiCarsDatabase(0, name, year, engineCapacity, createdAt))
                    database.idsDao().saveId(ApiAvailableForViewingIds(newId.toInt()))
                    prefs.saveAddAttemptNumber(attempt + 1)
                }
            }
        }
    }

    override suspend fun clearIds() {
        database.idsDao().deleteAllIds()
    }
}