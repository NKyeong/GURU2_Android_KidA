package com.example.guru2_android_kida.Challenge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.R

class ChallengeExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_explaination)


        // 챌린지 시작하기 버튼
        val btnStartChallenge: Button = findViewById(R.id.btn_start_challenge)
        btnStartChallenge.setOnClickListener {
            val intent = Intent(this, PersonalChallengeActivity::class.java)
            startActivity(intent)
        }
    }
}

