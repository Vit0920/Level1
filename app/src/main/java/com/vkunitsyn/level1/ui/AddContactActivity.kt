package com.vkunitsyn.level1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vkunitsyn.level1.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        processBackArrowClick()
    }

    private fun processBackArrowClick() {
        binding.ibArrowBackAddContact.setOnClickListener {
            finish()
        }
    }
}