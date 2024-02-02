package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment
import com.example.guru2_android_kida.ImageSliderAdapter
import com.example.guru2_android_kida.Indicator
import com.example.guru2_android_kida.R

class ChallengeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment에서 사용할 레이아웃을 인플레이트하여 반환합니다.
        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lifeBtn: ImageButton = view.findViewById(R.id.lifeBtn)
        val foodBtn: ImageButton = view.findViewById(R.id.foodBtn)
        val exerciseBtn: ImageButton = view.findViewById(R.id.exerciseBtn)
        val studyBtn: ImageButton = view.findViewById(R.id.studyBtn)
        val hobbyBtn: ImageButton = view.findViewById(R.id.hobbyBtn)
        val extraBtn: ImageButton = view.findViewById(R.id.extraBtn)

        // 각 버튼에 대한 클릭 이벤트 처리
        lifeBtn.setOnClickListener { navigateToCategory("생활습관") }
        foodBtn.setOnClickListener { navigateToCategory("식습관") }
        exerciseBtn.setOnClickListener { navigateToCategory("운동") }
        studyBtn.setOnClickListener { navigateToCategory("공부") }
        hobbyBtn.setOnClickListener { navigateToCategory("취미생활") }
        extraBtn.setOnClickListener { navigateToCategory("기타") }

        //

        //populatViewPage 이미지 슬라이드
        val popularViewPager: ViewPager2 = view.findViewById(R.id.popularViewPager)
        val adapter1 = ImageSliderAdapter()
        popularViewPager.adapter = adapter1

        //myChallengeViewPager 이미지 슬라이드
        val myChallengeViewPager: ViewPager2 = view.findViewById(R.id.myChallengeViewPager)
        val adapter2 = ImageSliderAdapter()
        myChallengeViewPager.adapter = adapter2

        // Set up ViewPager with the ImageSliderAdapter
        val imageSliderAdapter = ImageSliderAdapter()
        popularViewPager.adapter = imageSliderAdapter
        myChallengeViewPager.adapter = imageSliderAdapter

        // Find indicatorLayout
        val popularIndicatorLayout: LinearLayout = view.findViewById(R.id.popularIndicatorLayout)
        val recommendationIndicatorLayout: LinearLayout =
            view.findViewById(R.id.recommendationIndicatorLayout)

        // Set up Indicator
        val indicator1 = Indicator(5, requireContext()) // Assuming there are 5 images
        popularIndicatorLayout.addView(indicator1.createDotPanel())

        val indicator2 = Indicator(5, requireContext()) // Assuming there are 5 images
        recommendationIndicatorLayout.addView(indicator2.createDotPanel())

        // ViewPager Page Change Listener for popularIndicator
        popularViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator1.selectDot(position)
            }
        })

        // ViewPager Page Change Listener for recommendationIndicator
        myChallengeViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator2.selectDot(position)
            }
        })
    }

    // 카테고리 별 챌린지 페이지로 이동하는 함수
    private fun navigateToCategory(category: String) {
        val intent = Intent(context, ChallengeCategoryActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}
