package com.example.level1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Animation
        overridePendingTransition(com.google.android.material.R.anim.abc_grow_fade_in_from_bottom,
            com.google.android.material.R.anim.abc_shrink_fade_out_from_bottom)

        //Displays user name
        val userName = intent.extras?.getString("user_name")
        findViewById<TextView>(R.id.tv_name).text = userName


    }
}