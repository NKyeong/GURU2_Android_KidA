package com.example.guru2_android_kida.Challenge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.Login.DBHelper

class ChallengeExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_explaination)

        // DBHelper 인스턴스 생성
        val dbHelper = DBHelper(this)

        // '챌린지 시작하기' 버튼
        val btnStartChallenge: Button = findViewById(R.id.btn_start_challenge)
        btnStartChallenge.setOnClickListener {
            // 현재 로그인한 사용자 이름과 선택된 챌린지 이름 가져오기
            val currentUsername = dbHelper.getCurrentUsername()
            val selectedChallengeName = dbHelper.getSelectedChallengeName()

            // 두 값 모두 null이 아닌 경우에만 PersonalChallengeActivity로 이동
            if (currentUsername != null && selectedChallengeName != null) {
                val intent = Intent(this, PersonalChallengeActivity::class.java).apply {
                    putExtra("username", currentUsername)
                    putExtra("challengeName", selectedChallengeName)
                }
                startActivity(intent)
            }
        }
    }
}
