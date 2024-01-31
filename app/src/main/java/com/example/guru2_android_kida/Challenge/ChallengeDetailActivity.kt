package com.example.guru2_android_kida.Challenge

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.R

class ChallengeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        // 인텐트에서 사용자 이름과 챌린지 이름 받기
        val username = intent.getStringExtra("username") ?: "defaultUser"
        val challengeName = intent.getStringExtra("challengeName") ?: "defaultChallenge"

        val dbHelper = ChallengeDBHelper(this)

        // 도전과제 정보 조회
        val challengeInfo = dbHelper.getChallengeInfo(username, challengeName)

        // 뷰 바인딩
        val btnBack = findViewById<Button>(R.id.btnBack)
        val gridLayoutStamps = findViewById<GridLayout>(R.id.gridLayoutStamps)
        val tvChallenge1 = findViewById<TextView>(R.id.tvChallenge1)
        val tvChallenge2 = findViewById<TextView>(R.id.tvChallenge2)
        val tvChallenge3 = findViewById<TextView>(R.id.tvChallenge3)

        // 정보 설정
        if (challengeInfo != null) {
            tvChallenge1.text = challengeInfo.challenge1
        }
        if (challengeInfo != null) {
            tvChallenge2.text = challengeInfo.challenge2
        }
        if (challengeInfo != null) {
            tvChallenge3.text = challengeInfo.challenge3
        }

        // '뒤로 가기' 버튼 이벤트 처리
        btnBack.setOnClickListener {
            finish() // 현재 활동을 종료하고 이전 화면으로 돌아감
        }

        // 도장판 초기화
        if (challengeInfo != null) {
            initializeStampGrid(gridLayoutStamps, challengeInfo.stampsCollected)
        }
    }

    // 도장판 초기화 함수
    private fun initializeStampGrid(gridLayout: GridLayout, stampsCollected: Int) {
        val totalStamps = 5 * 6 // 5행 6열

        for (i in 0 until totalStamps) {
            val stamp = ImageView(this)
            if (i < stampsCollected) {
                // 채워진 도장 이미지 설정
                stamp.setImageResource(R.drawable.indicator_dot_selected)
            } else {
                // 빈 도장 이미지 설정
                stamp.setImageResource(R.drawable.indicator_dot_unselected)
            }
            gridLayout.addView(stamp)

            // 도장 이미지의 레이아웃 파라미터 설정
            val params = stamp.layoutParams as GridLayout.LayoutParams
            params.width = 0
            params.height = 0
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            stamp.layoutParams = params
        }
    }
}