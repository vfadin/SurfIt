package com.example.surfit.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.surfit.utils.Constants.IDS_TABLE_NAME

@Entity(tableName = IDS_TABLE_NAME)
data class ApiAvailableForViewingIds(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
)