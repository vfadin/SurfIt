package com.example.surfit.data.repo

import android.content.Context
import android.net.Uri
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
        return SharedPreferences(context).restorePurchaseToken() == "PAID_TOKEN"
    }

    override suspend fun getCars(): List<Car> {
        initialInsert()
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
                    val newId = insert(
                        ApiCarsDatabase(0, name, year, engineCapacity, "$photoUri", createdAt)
                    )
                    database.idsDao().saveId(ApiAvailableForViewingIds(newId.toInt()))
                    prefs.saveAddAttemptNumber(attempt + 1)
                }
            }
        }
    }

    override suspend fun clearIds() {
        database.idsDao().deleteAllIds()
    }

    private suspend fun initialInsert() {
        database.carsDao().apply {
            if (size() == 0) {
                insert(
                    ApiCarsDatabase(
                        0,
                        "Москвич 3",
                        2023,
                        2.0,
                        "https://lh3.googleusercontent.com/md7JIw44TfEtGhbw8J_vnSdsNJytork83Br4eft1YOFs56X0q3PChdoRgrrsU8WoWJ3UD1pp8yiXttZIpNrS9hKW4CkxwbxPLv2rQH5PtC9R=s1500",
                        createdAt = "15.03.2023"
                    )
                )
                insert(
                    ApiCarsDatabase(
                        0,
                        "Москвич 412",
                        1970,
                        1.0,
                        "https://s1.avtoclassika.com/uploads/files/news/49/files/moskwich-412-1.jpg",
                        createdAt = "15.03.2023"
                    )
                )
                insert(
                    ApiCarsDatabase(
                        0,
                        "Toyota",
                        2020,
                        2.0,
                        "",
                        createdAt = "15.03.2023"
                    )
                )
                insert(
                    ApiCarsDatabase(
                        0,
                        "BMW 5",
                        2023,
                        4.4,
                        "https://img-c.drive.ru/models.large.main.images/0000/000/000/001/b2a/48d957a0c1fb48cc-main.png",
                        createdAt = "16.03.2023"
                    )
                )
            }
        }
    }
}
