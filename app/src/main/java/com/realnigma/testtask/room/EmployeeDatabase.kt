package com.realnigma.testtask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.realnigma.notesapp.Converters
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Employee::class, Specialty::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao() : EmployeeDao

    @Volatile
    private var INSTANCE: EmployeeDatabase? = null

    fun getDatabase(
        context : Context,
        scope: CoroutineScope
    ) : EmployeeDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                EmployeeDatabase::class.java,
                "employee_database"
            )
                .fallbackToDestructiveMigrationFrom()
                .build()
            INSTANCE = instance
            instance
        }
    }

}