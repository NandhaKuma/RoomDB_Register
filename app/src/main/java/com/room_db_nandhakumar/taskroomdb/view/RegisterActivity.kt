package com.room_db_nandhakumar.taskroomdb.view

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.room_db_nandhakumar.taskroomdb.R
import com.room_db_nandhakumar.taskroomdb.databinding.ActivityRegisterBinding
import com.room_db_nandhakumar.taskroomdb.dialog.Alert
import com.room_db_nandhakumar.taskroomdb.utlis.SessionManager
import com.room_db_nandhakumar.taskroomdb.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var activityRegisterBinding: ActivityRegisterBinding

    //view Model

    private lateinit var registrationViewModel: RegistrationViewModel


    @Inject
    lateinit var alert: Alert

    @Inject
    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_register)

        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)


        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)


        val ss1 = SpannableString(resources.getString(R.string.already_have_account_login))
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@RegisterActivity, R.color.green)
                ds.isUnderlineText = false
            }
        }
        ss1.setSpan(clickableSpan1, 24, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        activityRegisterBinding.loginAlready.text = ss1
        activityRegisterBinding.loginAlready.movementMethod = LinkMovementMethod.getInstance()
        activityRegisterBinding.loginAlready.highlightColor = 0

        activityRegisterBinding.showPassword.setOnClickListener {
            if (activityRegisterBinding.password.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {

                activityRegisterBinding.showPassword.setImageResource(R.drawable.show_pwd)

                //show password
                activityRegisterBinding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()

                activityRegisterBinding.password.setSelection(activityRegisterBinding.password.text.toString().length)

            } else {

                activityRegisterBinding.showPassword.setImageResource(R.drawable.hide_pwd)

                //Hide Password
                activityRegisterBinding.password.transformationMethod = PasswordTransformationMethod.getInstance()

                activityRegisterBinding.password.setSelection(activityRegisterBinding.password.text.toString().length)


            }
        }



        activityRegisterBinding.regBtn.setOnClickListener {

            if (activityRegisterBinding.name.text.isNullOrEmpty()) {
                alert.makeToast(resources.getString(R.string.name_should_not_be_empty))
            } else if (activityRegisterBinding.email.text.isNullOrEmpty()) {
                alert.makeToast(resources.getString(R.string.email_should_not_be_empty))
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(activityRegisterBinding.email.text.toString()).matches()) {
                alert.makeToast(resources.getString(R.string.enter_valid_email_address))

            } else if (activityRegisterBinding.password.text.isNullOrEmpty()) {
                alert.makeToast(resources.getString(R.string.password_should_not_be_empty))
                activityRegisterBinding.password.isFocusable = false
            } else if (activityRegisterBinding.password.text.toString().trim().length < 8) {
                alert.makeToast(resources.getString(R.string.password_should_be_minimum))
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    var roomDetails = registrationViewModel.getRegistrationTable()
                    if (!roomDetails.isNullOrEmpty()) {
                        for (i in roomDetails.indices) {
                            if (roomDetails[i].email.equals(activityRegisterBinding.email.text.toString().trim())) {
                                alert.showSnackBar(activityRegisterBinding.email, resources.getString(R.string.email_already_register))
                            } else {
                                var id = sessionManager.getUserId().toInt() + 1
                                sessionManager.setUserId(id.toString())
                                registrationViewModel.insertRegistration(id, activityRegisterBinding.name.text.toString(), activityRegisterBinding.email.text.toString(), activityRegisterBinding.password.text.toString())
                                alert.showSnackBar(activityRegisterBinding.email, resources.getString(R.string.register_successfully))
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                        }
                    } else {
                        sessionManager.setUserId("1")
                        registrationViewModel.insertRegistration(1, activityRegisterBinding.name.text.toString(), activityRegisterBinding.email.text.toString(), activityRegisterBinding.password.text.toString())
                        alert.showSnackBar(activityRegisterBinding.email, resources.getString(R.string.register_successfully))
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                    }
                }
            }
        }
    }


}