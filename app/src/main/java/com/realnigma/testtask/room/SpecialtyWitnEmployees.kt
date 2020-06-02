package com.realnigma.testtask.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SpecialtyWithEmployees(
    @Embedded val specialty : Specialty,
    @Relation(
        parentColumn = "specialty_id",
        entityColumn = "employee_id",
        associateBy = Junction(EmployeeSpecialtyRef::class)
    )
        val employee : List<Employee>
)