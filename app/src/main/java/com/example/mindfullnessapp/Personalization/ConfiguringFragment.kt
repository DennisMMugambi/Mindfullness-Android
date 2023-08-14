package com.example.mindfullnessapp.Personalization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentConfiguringBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguringFragment : Fragment(R.layout.fragment_configuring) {

    private lateinit var binding : FragmentConfiguringBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConfiguringBinding.bind(view)
    }
}