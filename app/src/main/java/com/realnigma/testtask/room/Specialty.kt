package com.realnigma.testtask.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specialty(
    @PrimaryKey val specialty_id : Int,
    val name : String
)