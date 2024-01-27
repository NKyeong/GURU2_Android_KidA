package com.example.guru2_android_kida

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.guru2_android_kida.databinding.ActivityHomeBinding
import com.example.guru2_android_kida.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity: AppCompatActivity() {

    // 연결하고자 하는 xml 파일의 이름을 카멜 대소문자 형태로 변경하고 뒤에 Binding을 붙여 사용합니다.
    val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    // 메뉴를 클릭했을때 해당되는 플래그먼트로 이동할 수 있도록 만든 코드입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBottomNavigationView()

        // 앱 초기 실행 시 홈화면으로 설정
        // binding. 뒤에 지칭하고자 하는 요소의 아이디를 입력하고 수행합니다.
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
        }

    }

    // 메뉴의 아이디(fragment_home)를 선택했을 때 프래그먼트가 변경되는 코드 입니다.
    fun setBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
                    true
                }
                R.id.fragment_challenge -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, ChallengeFragment()).commit()
                    true
                }
                R.id.fragment_ranking -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, RankingFragment()).commit()
                    true
                }
                R.id.fragment_my_page -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, MyPageFragment()).commit()
                    true
                }
                else -> false
            }
        }

        /*val homeFragment = HomeFragment()
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
                R.id.fragment_my_page -> setCurrentFragment(myPageFragment)
            }
            true
        }*/

    }
    // 플래그먼트가 replace 됩니다.
    /*fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)
            commit()
        }*/

}
