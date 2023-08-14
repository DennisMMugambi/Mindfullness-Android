package com.example.mindfullnessapp.Home.Destinations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentConsultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsultFragment : Fragment(R.layout.fragment_consult) {

    private lateinit var binding: FragmentConsultBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConsultBinding.bind(view)
    }
}