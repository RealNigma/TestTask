package com.realnigma.testtask.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Specialty(
    @PrimaryKey val specialty_id : Int,
    @SerializedName("name") val name : String
)