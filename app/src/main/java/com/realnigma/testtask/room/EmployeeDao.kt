package com.realnigma.testtask.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getAllEmployees() : LiveData<List<Employee>>

    @Query("SELECT * from specialty")
    fun getAllSpecialties() : LiveData<List<Specialty>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee : Employee)

    @Insert
    fun insertSpecialty(specialties: List<Specialty>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployeeAndSpecialties(employee: Employee, specialties: List<Specialty>)

    @Query("DELETE from employee")
    fun deleteAll()
}