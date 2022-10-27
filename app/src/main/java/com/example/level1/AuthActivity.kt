package com.example.level1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.regex.Pattern

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val registerButton = findViewById<Button>(R.id.mb_register)
        val layoutPassword = findViewById<TextInputLayout>(R.id.tilo_password)
        val textInputPassword = findViewById<TextInputEditText>(R.id.tiet_password)
        val layoutEmail = findViewById<TextInputLayout>(R.id.tilo_email)
        val textInputEmail = findViewById<TextInputEditText>(R.id.tiet_email)
        val rememberCheckBox =findViewById<CheckBox>(R.id.cb_remember_me)
        val pref = applicationContext.getSharedPreferences("My pref", MODE_PRIVATE)
        val editor = pref.edit()


        textInputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = p0.toString()
                if (password.length >= 8) {
                    val pattern = Pattern.compile("[^a-zA-Z0-9]")
                    val matcher = pattern.matcher(password)
                    val containsSpecialChar = matcher.find()
                    if (containsSpecialChar) {
                        layoutPassword.helperText = getString(R.string.pass_helperText_stongPass)
                        layoutPassword.error = ""
                    } else {
                        layoutPassword.error = getString(R.string.pass_error_weak)
                    }
                } else {
                    layoutPassword.error = getString(R.string.pass_error_short)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        registerButton.setOnClickListener() {
            //Checks e-mail format
            val eMail = textInputEmail.text.toString()
            layoutEmail.error = ""
            if (eMail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(eMail).matches()){
                layoutEmail.error = getString(R.string.email_error_format)
            }

            //Register button won't work if either e-mail or password layouts display errors
            if (textInputPassword.text.isNullOrEmpty()){
                layoutPassword.error = getString(R.string.pass_error_empty)
            }else if(layoutPassword.error.isNullOrEmpty() && layoutEmail.error.isNullOrEmpty()){
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                intent.putExtra("user_name", parseEmail(eMail))
                startActivity(intent)
            }
            if (rememberCheckBox.isChecked){
                editor.putString("email", eMail)
                editor.putString("password", textInputPassword.text.toString())
                editor.apply()
            }
        }
    }


    //Parses e-mail into user name
    private fun parseEmail(eMail : String) : String {
        val userNameArr = eMail.substringBefore('@').split(".")
        var userName = ""
        for (part in userNameArr) {
            userName += part.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                else it.toString()
            } + " "
        }
        return userName.trim()
    }
}