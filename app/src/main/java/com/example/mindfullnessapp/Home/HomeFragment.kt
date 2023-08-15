package com.example.mindfullnessapp.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mindfullnessapp.Adapters.MeditationsAdapter
import com.example.mindfullnessapp.Domain.Models.MeditationItem
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        val meditationItemList = listOf(
            MeditationItem("Morning Meditation", R.drawable.feeling_awful),
            MeditationItem("Nature Sounds", R.drawable.feeling_awful),
            MeditationItem("Guided Meditation", R.drawable.feeling_awful),
            MeditationItem("Morning Meditation", R.drawable.feeling_awful),
            MeditationItem("Nature Sounds", R.drawable.feeling_awful),
            MeditationItem("Guided Meditation", R.drawable.feeling_awful),
            // Add more items as needed
        )

        val horizontalRecyclerView = binding.meditationsRV
        val adapter = MeditationsAdapter(meditationItemList)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerView.layoutManager = layoutManager
        horizontalRecyclerView.adapter = adapter

    }


}