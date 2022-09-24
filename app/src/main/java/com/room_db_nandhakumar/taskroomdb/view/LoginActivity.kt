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
import com.room_db_nandhakumar.taskroomdb.databinding.ActivityLoginBinding
import com.room_db_nandhakumar.taskroomdb.dialog.Alert
import com.room_db_nandhakumar.taskroomdb.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var activityLoginBinding: ActivityLoginBinding

    private lateinit var registrationViewModel: RegistrationViewModel

    @Inject
    lateinit var alert: Alert


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)


        val ss = SpannableString(resources.getString(R.string.dont_have_account_create_new))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@LoginActivity, R.color.green)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 22, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        activityLoginBinding.createAccount.text = ss
        activityLoginBinding.createAccount.movementMethod = LinkMovementMethod.getInstance()
        activityLoginBinding.createAccount.highlightColor = 0


        activityLoginBinding.showPassword.setOnClickListener {
            if(activityLoginBinding.password.transformationMethod.equals(PasswordTransformationMethod.getInstance())){

                activityLoginBinding.showPassword.setImageResource(R.drawable.show_pwd)

                //show password
                activityLoginBinding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()

                activityLoginBinding.password.setSelection(activityLoginBinding.password.text.toString().length)

            } else{

                activityLoginBinding.showPassword.setImageResource(R.drawable.hide_pwd)

                //Hide Password
                activityLoginBinding.password.transformationMethod = PasswordTransformationMethod.getInstance()

                activityLoginBinding.password.setSelection(activityLoginBinding.password.text.toString().length)


            }
        }



        activityLoginBinding.loginButton.setOnClickListener {
            if (activityLoginBinding.email.text.isNullOrEmpty()) {
                alert.makeToast(resources.getString(R.string.email_should_not_be_empty))
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(activityLoginBinding.email.text.toString()).matches()) {
                alert.makeToast(resources.getString(R.string.enter_valid_email_address))

            } else if (activityLoginBinding.password.text.isNullOrEmpty()) {
                alert.makeToast(resources.getString(R.string.password_should_not_be_empty))
            } else if (activityLoginBinding.password.text.toString().trim().length < 8) {
                alert.makeToast(resources.getString(R.string.password_should_be_minimum))
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    var roomDetails = registrationViewModel.getRegistrationTable()
                        if (!roomDetails.isNullOrEmpty()) {
                            for (i in roomDetails.indices) {
                                if (roomDetails[i].email.equals(activityLoginBinding.email.text.toString().trim()) && roomDetails[i].password.equals(activityLoginBinding.password.text.toString().trim())) {
                                    alert.showSnackBar(activityLoginBinding.email,resources.getString(R.string.login_successfully))
                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                } else {
                                    alert.showSnackBar(activityLoginBinding.email,resources.getString(R.string.invalied_email_password))
                                }
                            }
                        } else {
                            alert.showSnackBar(activityLoginBinding.email,resources.getString(R.string.please_register))
                        }
                }
            }
        }
    }




}