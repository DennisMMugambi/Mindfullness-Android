package com.example.mindfullnessapp.Personalization

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.dateTv.setPaintFlags(binding.dateTv.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        return binding.root
    }

}