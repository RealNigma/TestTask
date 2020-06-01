package com.realnigma.testtask.room

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithSpecialties(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "employee_id",
        entityColumn = "specialty_id"
    )
    val specialties: List<Specialty>
)