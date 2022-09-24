package com.room_db_nandhakumar.taskroomdb.utlis

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ActivityContext context: Context) {
    private var sharedPreferencesMode = 0
    private val sharedPreferencesName = "TASK ROOM DB"
    private var sharedPreferences = context.getSharedPreferences(sharedPreferencesName, sharedPreferencesMode)
    private var editor = sharedPreferences.edit()
    private var userId = "userId"

    fun setUserId(status: String) {
        editor.putString(userId, status)
        editor.commit()
    }

    fun getUserId(): String {
        return sharedPreferences.getString(userId, "").toString()
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }


}