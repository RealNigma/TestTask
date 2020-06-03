package com.realnigma.testtask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.realnigma.testtask.room.Employee
import com.realnigma.testtask.room.EmployeeDatabase
import com.realnigma.testtask.room.EmployeeSpecialtyRef
import com.realnigma.testtask.room.Specialty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : EmployeeRepository
    val specialties : LiveData<List<Specialty>>
    val employees : LiveData<List<Employee>>

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        specialties = repository.specialties
        employees = repository.employees
    }

    fun insertEmployeesAndSpecialties() = viewModelScope.launch(Dispatchers.IO) {
        repository.loadDataFromNetwork()
    }


}