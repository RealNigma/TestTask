package com.realnigma.testtask.room

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["employee_id", "specialty_id"], indices = [Index(value = ["employee_id", "specialty_id"])])
data class EmployeeSpecialtyRef(
    val employee_id : Int,
    val specialty_id : Int
)
