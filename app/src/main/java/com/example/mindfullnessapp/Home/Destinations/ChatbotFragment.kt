package com.example.mindfullnessapp.Home.Destinations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentChatbotBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatbotFragment : Fragment(R.layout.fragment_chatbot) {

    private lateinit var binding : FragmentChatbotBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChatbotBinding.bind(view)
    }
}