package com.example.mindfullnessapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.mindfullnessapp.R

class OnBoardingAdapter(var context: Context): PagerAdapter() {
    private val layoutInflater: LayoutInflater

    private val splashscreenDescriptions = arrayOfNulls<String>(3)
    private val splashscreenimages = intArrayOf(
        R.drawable.therapist, R.drawable.chatbotconvo,
        R.drawable.meditate
    )
    init {
        layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    // // Returns the number of pages/items to be displayed in the ViewPager.
    override fun getCount(): Int {
        return splashscreenimages.size

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = layoutInflater.inflate(R.layout.layout_onboard, container, false)

        val imgSplashScreen: ImageView

        imgSplashScreen = view.findViewById(R.id.iv_slider)
//        tvSplashScreenDescri=view.findViewById(R.id.tv_slider_descr)

        //   tvSplashScreenDescri.text=splashscreenDescriptions[position]

        imgSplashScreen.setImageResource(splashscreenimages[position])

        container.addView(view)

        return view

    }
    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as ConstraintLayout)
    }

}
