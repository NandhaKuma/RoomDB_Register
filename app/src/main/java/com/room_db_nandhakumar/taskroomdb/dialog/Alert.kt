package com.room_db_nandhakumar.taskroomdb.dialog

import android.content.Context

import android.view.View

import android.widget.Toast

import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make





class Alert @Inject constructor(@ActivityContext var context: Context) {

    fun makeToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(view: View?, text: String?) {
        make(view!!, text!!, Snackbar.LENGTH_SHORT).show()
    }


}

