package com.realnigma.testtask.network

import io.reactivex.Single
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployeesWithSpecialities() : Single<EmployeeResponse>
}