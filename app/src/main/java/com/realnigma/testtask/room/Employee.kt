package com.realnigma.testtask.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true) val employee_id : Int,
    val f_name : String?,
    val l_name : String?,
    val birthday : Date?,
    val avatr_url : String?
    )
