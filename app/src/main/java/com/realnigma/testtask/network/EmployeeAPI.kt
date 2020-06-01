package com.realnigma.testtask.network

import com.realnigma.testtask.room.EmployeeWithSpecialties
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployeesWithSpecialities() : Call<List<EmployeeWithSpecialties>>
}