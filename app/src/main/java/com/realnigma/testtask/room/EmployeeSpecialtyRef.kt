package com.realnigma.testtask.room

import androidx.room.Entity

@Entity(primaryKeys = ["employee_id", "specialty_id"])
data class EmployeeSpecialtyRef(
    val employee_id : Int,
    val specialty_id : Int
)