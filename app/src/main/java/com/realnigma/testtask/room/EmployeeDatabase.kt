package com.realnigma.testtask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Employee::class, Specialty::class, EmployeeSpecialtyRef::class], version = 5)
@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao() : EmployeeDao

    companion object {

    @Volatile
    private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EmployeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(EmployeeDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class EmployeeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    database.clearAllTables()
                }
            }
        }
    }

}