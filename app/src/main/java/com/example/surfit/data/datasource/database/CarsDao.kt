package com.example.surfit.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surfit.data.dto.ApiCarsDatabase
import com.example.surfit.utils.Constants.TABLE_NAME

@Dao
interface CarsDao {
    @Insert
    suspend fun insertAll(list: List<ApiCarsDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: ApiCarsDatabase)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAll(): List<ApiCarsDatabase>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getById(id: Int): ApiCarsDatabase

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    suspend fun size(): Int
}