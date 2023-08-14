package com.example.mindfullnessapp.Personalization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentCurrentProblemsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentProblemsFragment : Fragment(R.layout.fragment_current_problems) {

    private lateinit var binding : FragmentCurrentProblemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCurrentProblemsBinding.bind(view)

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_problemsFragment_to_configuringFragment)
        }
    }
}