package com.realnigma.testtask.network

import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("response") val response: List<RemoteResponse>)
