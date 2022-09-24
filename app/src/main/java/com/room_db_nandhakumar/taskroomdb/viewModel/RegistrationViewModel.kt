package com.room_db_nandhakumar.taskroomdb.viewModel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.room_db_nandhakumar.taskroomdb.repository.RegistrationRepository
import com.room_db_nandhakumar.taskroomdb.room.RegistrationTable
import com.room_db_nandhakumar.taskroomdb.utlis.NetworkStatus

class RegistrationViewModel @ViewModelInject constructor(private val registrationRepository: RegistrationRepository, private val networkStatus: NetworkStatus, private val application: Application) : ViewModel() {

    fun insertRegistration(id:Int,name:String,email:String,password:String)
    {
        registrationRepository.insertRegistration(id,name,email,password)
    }


    fun getRegistrationTable(): List<RegistrationTable> {
        return registrationRepository.getRegistrationTable()
    }





}