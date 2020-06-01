package com.realnigma.testtask

import androidx.lifecycle.LiveData
import com.realnigma.testtask.dagger.DaggerEmployeeAPIComponent
import com.realnigma.testtask.network.EmployeeAPI
import com.realnigma.testtask.room.Employee
import com.realnigma.testtask.room.EmployeeDao
import com.realnigma.testtask.room.Specialty
import javax.inject.Inject

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    @Inject
    lateinit var api : EmployeeAPI

    init {
        DaggerEmployeeAPIComponent.create().inject(this)
    }

    val employees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    val specialties: LiveData<List<Specialty>> = employeeDao.getAllSpecialties()
}