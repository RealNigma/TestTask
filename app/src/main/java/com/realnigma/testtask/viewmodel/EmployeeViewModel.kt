package com.realnigma.testtask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.realnigma.testtask.repository.EmployeeRepository
import com.realnigma.testtask.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : EmployeeRepository
    val specialties : LiveData<List<Specialty>>
    var specialtyId = MutableLiveData<Int>()
    var employeeId = MutableLiveData<Int>()
    var specialtyById : LiveData<SpecialtyWithEmployees>? = null
    var employeeById: LiveData<EmployeeWithSpecialties>? = null

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application, viewModelScope).employeeDao()
        repository = EmployeeRepository(employeeDao)
        specialties = repository.specialties
    }

    fun insertEmployeesAndSpecialties() = viewModelScope.launch(Dispatchers.IO) {
        repository.loadDataFromNetwork()
    }

    fun getSpecialtyById(specialtyId : Int) = viewModelScope.launch(Dispatchers.IO) {
        specialtyById = repository.getSpecialtyById(specialtyId)
    }

    fun getEmployeeById(employeeId : Int) = viewModelScope.launch(Dispatchers.IO) {
        employeeById = repository.getEmployeeById(employeeId)
    }

    private fun clearDisposable()= viewModelScope.launch(Dispatchers.IO) {
        repository.clearDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        clearDisposable()
    }


}