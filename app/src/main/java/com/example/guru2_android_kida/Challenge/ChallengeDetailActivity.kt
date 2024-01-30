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

        // 도장 개수 조회
        val dbHelper = ChallengeDBHelper(this)
        val stampsCollected = dbHelper.getStampsCollected("user1") // "user1"은 예시 사용자 이름입니다.

        // 도장판 초기화
        initializeStampGrid(gridLayoutStamps, stampsCollected)

        // 저장된 도전과제 표시 (Intent로 전달된 데이터 사용)
        val challenge1 = intent.getStringExtra("challenge1")
        val challenge2 = intent.getStringExtra("challenge2")
        val challenge3 = intent.getStringExtra("challenval dbHelper\n = ChallengeDBHelper(this)\n" +
                "        val stampsCollected = dbHelper.getStampsCollected(\"user1\") // \"user1\"은 예시 사용자 이름입니다.\n" +
                "\n" +
                "        // 도장판 초기화\n" +
                "        initializeStampGrid(gridLayoutStamps, stampsCollected)ge3")
        tvChallenge1.text = challenge1
        tvChallenge2.text = challenge2
        tvChallenge3.text = challenge3

    }

    // 현재 진행 중인 도장판 초기화 함수
    private fun initializeStampGrid(gridLayout: GridLayout, stampsCollected: Int) {
        val totalStamps = 5 * 6 // 5행 6열

        for (i in 0 until totalStamps) {
            val stamp = ImageView(this)
            if (i < stampsCollected) {
                // 채워진 도장 이미지 설정
                stamp.setImageResource(R.drawable.indicator_dot_selected) // 채워진 도장 이미지 리소스
            } else {
                // 빈 도장 이미지 설정
                stamp.setImageResource(R.drawable.indicator_dot_unselected) // 빈 도장 이미지 리소스
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