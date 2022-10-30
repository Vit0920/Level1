package com.example.level1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Animation
        overridePendingTransition(com.google.android.material.R.anim.abc_grow_fade_in_from_bottom,
            com.google.android.material.R.anim.abc_shrink_fade_out_from_bottom)

        //Displays user name
        binding.tvName.text = intent.extras?.getString(Constants.USER_NAME)
    }
}