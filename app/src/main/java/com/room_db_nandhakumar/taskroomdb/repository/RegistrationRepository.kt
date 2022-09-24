package com.room_db_nandhakumar.taskroomdb.repository

import android.app.Application
import com.room_db_nandhakumar.taskroomdb.room.MyDatabase
import com.room_db_nandhakumar.taskroomdb.room.RegistrationTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationRepository @Inject constructor(application: Application) {

    private val database = MyDatabase.getChatDatabase(application)




    fun insertRegistration(id:Int,name:String,email:String,password:String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val registerTable = RegistrationTable(id,email, name, password)
            database.myDao().insertRegistration(registerTable)

        }
    }


    fun getRegistrationTable() = database.myDao().getRegistrationTable()





}