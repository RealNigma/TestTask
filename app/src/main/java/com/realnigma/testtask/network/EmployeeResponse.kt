package com.realnigma.testtask.network

import com.google.gson.annotations.SerializedName
import com.realnigma.testtask.room.Employee
import com.realnigma.testtask.room.EmployeeWithSpecialties
import com.realnigma.testtask.room.Specialty

data class EmployeeResponse(
    @SerializedName("response") val response: List<RemoteResponse>)
