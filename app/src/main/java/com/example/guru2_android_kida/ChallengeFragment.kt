package com.example.guru2_android_kida

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment

class ChallengeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment에서 사용할 레이아웃을 인플레이트하여 반환합니다.
        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val recommendationIndicatorLayout: LinearLayout = view.findViewById(R.id.recommendationIndicatorLayout)

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
        myChallengeViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator2.selectDot(position)
            }
        })

        // 세번째 메뉴(3rd menu) 클릭 시 이벤트 처리
        val thirdMenuLayout: LinearLayout = view.findViewById(R.id.popularIndicatorLayout)
        thirdMenuLayout.setOnClickListener {
            // 챌린지 상세 화면으로 이동하는 인텐트 생성
            val intent = Intent(this, ChallengeExplanationActivity::class.java)
            // 필요한 경우 챌린지에 대한 정보를 인텐트에 추가할 수 있습니다.
            // intent.putExtra("challengeId", challengeId) 등
            startActivity(intent)
    }
}
