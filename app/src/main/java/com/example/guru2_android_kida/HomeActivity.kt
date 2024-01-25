package com.example.guru2_android_kida

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.bottom_navigation_view
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity: AppCompatActivity() {
    // 메뉴를 클릭했을때 해당되는 플래그먼트로 이동할 수 있도록 만든 코드입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        val challengeFragment = ChallengeFragment()
        val rankingFragment = RankingFragment()
        val myPageFragment = MyPageFragment()

        // 초기화면을 홈 플래그먼트로 설정합니다.
        setCurrentFragment(homeFragment)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.fragment_home -> setCurrentFragment(homeFragment)
                R.id.fragment_challenge -> setCurrentFragment(challengeFragment)
                R.id.fragment_ranking -> setCurrentFragment(rankingFragment)
                R.id.fragment_mypage -> setCurrentFragment(myPageFragment)
            }
            true
        }

    }
    // 플래그먼트가 replace 됩니다.
    fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)
            commit()
        }

}
