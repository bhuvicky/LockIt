package com.bhuvanesh.lockit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//@Database(entities = arrayOf(EmployeeRecord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        // Double checked locking for singleton
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "employee-list.db"
        ).build()
    }
}