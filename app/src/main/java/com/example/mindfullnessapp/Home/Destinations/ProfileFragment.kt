package com.example.mindfullnessapp.Home.Destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding : FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
    }
}