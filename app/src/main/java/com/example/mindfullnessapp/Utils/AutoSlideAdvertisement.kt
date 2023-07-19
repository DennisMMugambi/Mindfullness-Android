package com.ekenya.rnd.android.common.Utils

import android.os.Handler
import androidx.viewpager.widget.ViewPager
import java.util.*

public class AutoSlideAdvertisement {

    private var pagerPosition = 0

    public fun autoPlayAdvertisement(viewPager: ViewPager) {
        Handler().postDelayed(Runnable {
            if (pagerPosition == Objects.requireNonNull(viewPager.adapter)!!.count - 1
            ) {
                pagerPosition = 0
                viewPager.currentItem = pagerPosition
            } else {
                viewPager.currentItem = 1.let {
                    pagerPosition += it;
                    pagerPosition
                }
            }
            autoPlayAdvertisement(viewPager)
        }, 4000)
    }

}