package com.vkunitsyn.level1.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.databinding.ActivityProfileBinding
import com.vkunitsyn.level1.utils.Constants

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Animation
        overridePendingTransition(com.google.android.material.R.anim.abc_grow_fade_in_from_bottom,
            com.google.android.material.R.anim.abc_shrink_fade_out_from_bottom)

        //Displays user name
        binding.tvName.text = intent.extras?.getString(Constants.USER_NAME)

        proccessViewContactasButtonClick()
    }

    private fun proccessViewContactasButtonClick() {
        binding.mbViewContacts.setOnClickListener(){
            val intent = Intent(this@ProfileActivity, ContactsActivity::class.java)
            startActivity(intent)
        }
    }
}