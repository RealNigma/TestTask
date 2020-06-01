package com.realnigma.testtask.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getAllEmployees() : LiveData<List<Employee>>

    @Query("SELECT * from specialty")
    fun getAllSpecialties() : LiveData<List<Specialty>>

    @Insert
    fun insertEmployeeAndSpecialties(employee: Employee, specialties: List<Specialty>)
}