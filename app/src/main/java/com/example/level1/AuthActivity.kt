package com.example.level1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.databinding.ActivityAuthBinding
import java.util.*
import java.util.regex.Pattern


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = applicationContext.getSharedPreferences("My pref", MODE_PRIVATE)

        fillInSavedData()
        controlPasswordInput()
        processRegisterButtonClick()
    }

    private fun fillInSavedData() {
        prefs = getPreferences(MODE_PRIVATE)
        binding.tietEmail.setText(prefs.getString("email", ""))
        binding.tietPassword.setText(prefs.getString("password", ""))
    }

    private fun processRegisterButtonClick() {
        binding.mbRegister.setOnClickListener() {

            checkEmail(binding.tietEmail.text.toString())
            checkIfPasswordIsEmpty()
            //Register button won't work if either e-mail or password layouts display errors
            if (noInputErrors()) {
                processSharedPreferences()
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                intent.putExtra("user_name", parseEmail(binding.tietEmail.text.toString()))
                startActivity(intent)
            }

        }
    }

    //Displays error is the password is empty
    private fun checkIfPasswordIsEmpty() {
        if (binding.tietPassword.text.isNullOrEmpty()) {
            binding.tiloPassword.error = getString(R.string.pass_error_empty)
        }
    }

    //Checks if there are any errors in password or e-mail input layouts
    private fun noInputErrors(): Boolean {
        return binding.tiloPassword.error.isNullOrEmpty() && binding.tiloEmail.error.isNullOrEmpty()
    }

    //Saves or clears SharedPreferences depending on the "remember me" checkbox state.
    private fun processSharedPreferences() {
        with(prefs.edit()) {
            if (binding.cbRememberMe.isChecked) {
                putString("email", binding.tietEmail.text.toString())
                putString("password", binding.tietPassword.text.toString())
                apply()
            } else {
                clear()
                apply()
            }
        }
    }

    //Checks e-mail format
    private fun checkEmail(email: String) {
        binding.tiloEmail.error = ""
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tiloEmail.error = getString(R.string.email_error_format)
        }
    }

    /*Controls the process of password input. Displays errors in case the password doesn't meet
       certain criteria*/
    private fun controlPasswordInput() {
        binding.tietPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = p0.toString()
                with(binding.tiloPassword) {
                    if (password.length >= 8) {
                        val pattern = Pattern.compile("[^a-zA-Z0-9]")
                        val matcher = pattern.matcher(password)
                        val containsSpecialChar = matcher.find()
                        if (containsSpecialChar) {
                            helperText = getString(R.string.pass_helperText_stongPass)
                            error = ""
                        } else {
                            error = getString(R.string.pass_error_weak)
                        }
                    } else {
                        error = getString(R.string.pass_error_short)
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    //Parses the e-mail into user name
    private fun parseEmail(eMail: String): String {
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