package com.example.mindfullnessapp.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindfullnessapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_menu)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}