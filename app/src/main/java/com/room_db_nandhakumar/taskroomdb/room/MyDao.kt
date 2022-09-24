package com.room_db_nandhakumar.taskroomdb.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MyDao {

    @Query("Select * from RegistrationDetails")
    fun getRegistrationTable(): List<RegistrationTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegistration(register: RegistrationTable)




}