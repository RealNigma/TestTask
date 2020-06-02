package com.realnigma.testtask.network

import com.realnigma.testtask.room.Specialty

data class RemoteResponse(
    val f_name : String,
    val l_name : String,
   // val birthday : Date?,
    val avatr_url : String,
    val specialty : List<Specialty>)