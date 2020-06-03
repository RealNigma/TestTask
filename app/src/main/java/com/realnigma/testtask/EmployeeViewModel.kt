package com.realnigma.testtask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.realnigma.testtask.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : EmployeeRepository
    val specialties : LiveData<List<SpecialtyWithEmployees>>
    val employees : LiveData<List<EmployeeWithSpecialties>>
    val ref : LiveData<List<EmployeeSpecialtyRef>>

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        specialties = repository.specialties
        employees = repository.employees
        ref = repository.ref
    }

    fun insertEmployeesAndSpecialties() = viewModelScope.launch(Dispatchers.IO) {
        repository.loadDataFromNetwork()
    }


}