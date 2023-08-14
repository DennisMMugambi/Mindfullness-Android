package com.example.mindfullnessapp.Home.Destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentMeditationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeditationFragment : Fragment(R.layout.fragment_meditation) {

    private lateinit var binding : FragmentMeditationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMeditationBinding.bind(view)
    }

}