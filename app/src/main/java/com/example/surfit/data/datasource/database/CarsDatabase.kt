package com.example.surfit.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.surfit.data.dto.ApiCarsDatabase
import com.example.surfit.utils.Constants.TABLE_NAME

@Database(entities = [ApiCarsDatabase::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun dao(): CarsDao

    companion object {
        @Volatile
        private var INSTANCE: CarsDatabase? = null

        fun getDatabase(context: Context): CarsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CarsDatabase::class.java, TABLE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}