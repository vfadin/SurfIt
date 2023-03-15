package com.example.surfit.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
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
    val createdAt: String
)
