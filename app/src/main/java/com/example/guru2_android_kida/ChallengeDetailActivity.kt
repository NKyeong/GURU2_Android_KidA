package com.example.challengeapp

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

        // 뷰 바인딩
        val btnBack = findViewById<Button>(R.id.btnBack)
        val gridLayoutStamps = findViewById<GridLayout>(R.id.gridLayoutStamps)
        val tvChallenge1 = findViewById<TextView>(R.id.tvChallenge1)
        val tvChallenge2 = findViewById<TextView>(R.id.tvChallenge2)
        val tvChallenge3 = findViewById<TextView>(R.id.tvChallenge3)

        // '뒤로 가기' 버튼 이벤트 처리
        btnBack.setOnClickListener {
            finish() // 현재 활동을 종료하고 이전 화면으로 돌아감
        }

        // 저장된 도전과제 표시 (Intent로 전달된 데이터 사용)
        val challenge1 = intent.getStringExtra("challenge1")
        val challenge2 = intent.getStringExtra("challenge2")
        val challenge3 = intent.getStringExtra("challenge3")
        tvChallenge1.text = challenge1
        tvChallenge2.text = challenge2
        tvChallenge3.text = challenge3

        // 현재 진행 중인 도장판 초기화 (실제 도장 데이터를 추가해야 함)
        initializeStampGrid(gridLayoutStamps)
    }

    // 현재 진행 중인 도장판 초기화 함수
    private fun initializeStampGrid(gridLayout: GridLayout) {
        for (i in 1..30) {
            val stamp = ImageView(this)
            // 도장 이미지 설정
            stamp.setImageResource(R.drawable.indicator_dot_unselected) // 임시로 수정하였습니다.
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
