package com.realnigma.testtask.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Transaction
    @Query("SELECT * FROM employee")
    fun getAllEmployees() : LiveData<List<EmployeeWithSpecialties>>

    @Query("SELECT * from specialty")
    fun getAllSpecialties() : LiveData<List<SpecialtyWithEmployees>>

    @Query("SELECT * from employee WHERE employee_id = :id")
    fun getEmployeeById(id : Int) : LiveData<List<Employee>>

    @Query("SELECT * from employeespecialtyref")
    fun getRefData(): LiveData<List<EmployeeSpecialtyRef>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)


    fun insertEmployee(employee : Employee) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialty(specialty: Specialty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRef(employeeSpecialtyRef: EmployeeSpecialtyRef)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployeeAndSpecialties(employee: Employee, specialties: List<Specialty>)

    @Query("DELETE FROM employee")
    fun deleteAll()
}