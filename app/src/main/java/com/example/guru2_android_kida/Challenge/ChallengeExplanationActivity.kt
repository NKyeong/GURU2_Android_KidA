package com.example.guru2_android_kida.Challenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.R

class ChallengeExplanationActivity : AppCompatActivity() {
    private lateinit var challengeDBHelper: ChallengeDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_explaination)


        /*challengeDBHelper = ChallengeDBHelper(this)

        // 챌린지 시작하기 버튼
        val btnStartChallenge: Button = findViewById(R.id.btn_start_challenge)
        btnStartChallenge.setOnClickListener {

            // 현재 로그인한 사용자의 이름을 가져오기
            val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val currentUsername = sharedPreferences.getString("current_username", "") ?: ""

            if (currentUsername.isNotBlank()) {
                // 로그인한 사용자가 있을 경우의 처리
            }
            // 사용자가 참여한 챌린지 이름 가져오기
            //val joinedChallenges = challengeDBHelper.getJoinedChallengesForUser(currentUsername)

            val intent = Intent(this, PersonalChallengeActivity::class.java)
            startActivity(intent)
        }*/
    }
}