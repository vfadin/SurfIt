package com.example.surfit.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surfit.data.dto.ApiAvailableForViewingIds
import com.example.surfit.utils.Constants

@Dao
interface IdsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveId(id: ApiAvailableForViewingIds)

    @Query("SELECT COUNT(*) FROM ${Constants.IDS_TABLE_NAME}")
    suspend fun sizeOfIds(): Int

    @Query("SELECT * FROM ${Constants.IDS_TABLE_NAME}")
    suspend fun getAllIds(): List<ApiAvailableForViewingIds>

    @Query("DELETE FROM ${Constants.IDS_TABLE_NAME}")
    suspend fun deleteAllIds()
}