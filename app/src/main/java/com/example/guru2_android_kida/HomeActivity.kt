package com.example.guru2_android_kida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.Challenge.ChallengeFragment
import com.example.guru2_android_kida.MyPage.MyPageFragment
import com.example.guru2_android_kida.Ranking.RankingFragment
import com.example.guru2_android_kida.databinding.ActivityHomeBinding

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

    }
}
