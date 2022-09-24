package com.room_db_nandhakumar.taskroomdb.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "RegistrationDetails")
class RegistrationTable (
    @PrimaryKey
    @SerializedName("id")
    val id:Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?
    )