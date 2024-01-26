package com.example.guru2_android_kida

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class ChallengeFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_challenge)

        //populatViewPage 이미지 슬라이드
        val popularViewPager: ViewPager2 = findViewById(R.id.popularViewPager)
        val adapter1 = ImageSliderAdapter()
        popularViewPager.adapter = adapter1

        //myChallengeViewPager 이미지 슬라이드
        val myChallengeViewPager: ViewPager2 = findViewById(R.id.myChallengeViewPager)
        val adapter2 = ImageSliderAdapter()
        myChallengeViewPager.adapter = adapter2

        // Set up ViewPager with the ImageSliderAdapter
        val imageSliderAdapter = ImageSliderAdapter()
        popularViewPager.adapter = imageSliderAdapter
        myChallengeViewPager.adapter = imageSliderAdapter


        // Find indicatorLayout
        val popularIndicatorLayout: LinearLayout = findViewById(R.id.popularIndicatorLayout)
        val recommendationIndicatorLayout: LinearLayout = findViewById(R.id.recommendationIndicatorLayout)

        // Set up Indicator
        val indicator1 = Indicator(5, this) // Assuming there are 5 images
        popularIndicatorLayout.addView(indicator1.createDotPanel())

        val indicator2 = Indicator(5, this) // Assuming there are 5 images
        recommendationIndicatorLayout.addView(indicator2.createDotPanel())


        // ViewPager Page Change Listener for popularIndicator
        popularViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator1.selectDot(position)
            }
        })

        // ViewPager Page Change Listener for recommendationIndicator
        myChallengeViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator2.selectDot(position)
            }
        })
    }
}
