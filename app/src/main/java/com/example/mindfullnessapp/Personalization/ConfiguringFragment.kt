package com.example.mindfullnessapp.Personalization

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mindfullnessapp.Home.HomeActivity
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentConfiguringBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguringFragment : Fragment(R.layout.fragment_configuring) {

    private lateinit var binding : FragmentConfiguringBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConfiguringBinding.bind(view)

        val handler = Handler()

        println("Starting execution...")

        handler.postDelayed({
            // Code to execute after 3 seconds
            val intent = Intent(context, HomeActivity::class.java)
            //  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 7000) // Delay for 7 seconds
    }
}