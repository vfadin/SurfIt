package com.example.surfit.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.surfit.utils.Constants.CARS_TABLE_NAME

@Entity(tableName = CARS_TABLE_NAME)
data class ApiCarsDatabase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = -1,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val year: Int,
    @ColumnInfo
    val engineCapacity: Double,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val createdAt: String
)
