package com.example.mindfullnessapp.Onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.ekenya.rnd.android.common.Utils.AutoSlideAdvertisement
import com.example.mindfullnessapp.Adapters.OnBoardingAdapter
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.FragmentOnBoardingBinding

class OnboardingFragment : Fragment() {


    private lateinit var binding : FragmentOnBoardingBinding

    private lateinit var parentView : ConstraintLayout

    private var isLastScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Override status bar color in theme for this fragment
        // activity?.window?.statusBarColor =  resources.getColor(com.ekenya.rnd.android.common.R.color.white)

        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        parentView = binding.parentView


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigate to next screen via onclick listener
        //checkFirstTimeLogin()

        loadSliders()
    }


    private fun loadSliders(){
        val onBoardingAdapter =
            OnBoardingAdapter(
                requireContext()
            )
        binding.pager.adapter =onBoardingAdapter
        binding.tDots.setupWithViewPager(binding.pager, true)
        onBoardingAdapter.notifyDataSetChanged()

        binding.tvSliderTitle.setText(R.string.onboarding_title_one)
        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> {
                        binding.tvSliderDescr.setText(R.string.screen_1_description)
                        binding.tvSliderTitle.setText(R.string.onboarding_title_one)
                        binding.btnGetStarted.setText(R.string.next)
                    }
                    1 -> {
                        binding.tvSliderDescr.setText(R.string.screen_2_description)
                        binding.tvSliderTitle.setText(R.string.onboarding_title_two)
                        binding.btnGetStarted.setText(R.string.next)
                    }
                    2-> {
                        binding.tvSliderDescr.setText(R.string.screen_3_description)
                        binding.tvSliderTitle.setText(R.string.onboarding_title_three)
                        binding.btnGetStarted.setText(R.string.get_started)
                    }
                    else -> {
                        // binding.tvSliderDescr.setText(R.string.screen_1_description)
                        // binding.tvSliderTitle.setText(R.string.onboarding_title_one)
                    }
                }
            }

            override fun onPageSelected(position: Int) {



            }
        })

        scrollToNext()
        val autoPlay = AutoSlideAdvertisement()
        autoPlay.autoPlayAdvertisement(binding.pager)

    }

    private fun scrollToNext() {
        binding.btnGetStarted.setOnClickListener {
            when(binding.pager.currentItem) {
                0 -> { binding.pager.currentItem += 1}
                1 -> { binding.pager.currentItem += 1}
                2 -> { isLastScreen = true}
            }

            if (isLastScreen) {
                binding.loading.visibility = View.VISIBLE
               // showAuthModule()
                findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }

        binding.skipBtn.setOnClickListener {
            binding.pager.currentItem = 2
            // binding.btnGetStarted.setText(R.string.get_started)
        }
    }

}