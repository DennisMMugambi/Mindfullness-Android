package com.example.mindfullnessapp.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mindfullnessapp.Adapters.GenericAdapter
import com.example.mindfullnessapp.Adapters.MeditationsAdapter
import com.example.mindfullnessapp.Domain.Models.MeditationItem
import com.example.mindfullnessapp.Domain.Models.TherapistItem
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

        showTherapists()
    }

    private fun showTherapists() {
        // Suppose you have a list of items with different types
       val therapistList: List<TherapistItem> = listOf(
            TherapistItem(
                image = R.drawable.feeling_awful,
                name = "Dr. Panoor Doe",
                rating = "4.8",
                education = "PhD in Psychology",
                experience = "10+ years of experience",
                price = "KSHS 5000",
                oldprice = "KSHS 7000",
                availability = "9AM to 1AM"
            ),
            TherapistItem(
                image = R.drawable.feeling_awful,
                name = "Dr Janic Doe",
                rating = "4.1",
                education = "MA in Counseling",
                experience = "8+ years of experience",
                price = "KSHS 4000",
                oldprice = "KSHS 4500",
                availability = "2PM to 5PM"
            ),
           TherapistItem(
               image = R.drawable.feeling_awful,
               name = "Dr Kevin Doe",
               rating = "4.7",
               education = "MA in Psychiatry",
               experience = "12+ years of experience",
               price = "KSHS 6000",
               oldprice = "KSHS 7500",
               availability = "9AM to 5PM"
           ),
            // Add more therapists here...
        )

        // Create an instance of the GenericAdapter
        val adapter = GenericAdapter(therapistList, R.layout.therapist_card) { itemView, item ->
            // Bind your data to the itemView
            // You can access the views in itemView using findViewById or ViewBinding
            itemView.findViewById<ImageView>(R.id.therapist_image).setImageResource(item.image)
            itemView.findViewById<TextView>(R.id.therapist_title).text = item.name
            itemView.findViewById<TextView>(R.id.therapist_rating).text = item.rating
            itemView.findViewById<TextView>(R.id.therapist_education).text = item.education
            itemView.findViewById<TextView>(R.id.therapist_experience).text = item.experience
            itemView.findViewById<TextView>(R.id.therapist_price).text = item.price
            itemView.findViewById<TextView>(R.id.therapist__old_price).text = item.oldprice
            itemView.findViewById<TextView>(R.id.therapist_availability_time).text = item.availability
        }

        // Set the adapter to your RecyclerView
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.therapistsRV.layoutManager = layoutManager
        binding.therapistsRV.adapter = adapter

    }

    private fun hideBottomNavOnScroll() {

    }


}