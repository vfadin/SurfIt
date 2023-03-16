package com.example.surfit.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surfit.data.dto.ApiCarsDatabase
import com.example.surfit.utils.Constants.CARS_TABLE_NAME

@Dao
interface CarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ApiCarsDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: ApiCarsDatabase): Long

    @Query("DELETE FROM $CARS_TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM $CARS_TABLE_NAME")
    suspend fun getAll(): List<ApiCarsDatabase>

    @Query("SELECT * FROM $CARS_TABLE_NAME WHERE id = :id")
    suspend fun getById(id: Int): ApiCarsDatabase?

    @Query("SELECT COUNT(*) FROM $CARS_TABLE_NAME")
    suspend fun size(): Int
}