package com.example.mindfullnessapp.Personalization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentPersonalizationBinding

class PersonalizationFragment : Fragment() {

    private lateinit var binding : FragmentPersonalizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPersonalizationBinding.inflate(inflater)
        return binding.root
    }

}