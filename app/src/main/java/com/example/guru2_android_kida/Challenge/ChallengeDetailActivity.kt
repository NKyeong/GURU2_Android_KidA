package com.example.guru2_android_kida.Challenge

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2_android_kida.R

class ChallengeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        val gridLayout: GridLayout = findViewById(R.id.stampBoard)

        for (i in 0 until gridLayout.childCount) {
            val child: View = gridLayout.getChildAt(i)

            if (child is ImageButton) {
                child.setOnClickListener {

                    changeImageButtonImage(child)
                }
            }
        }

        val challengeName = intent.getStringExtra("challengeName")

        // 뷰 바인딩
        val btnBack = findViewById<Button>(R.id.btnBack)

        // '뒤로 가기' 버튼 이벤트 처리
        btnBack.setOnClickListener {
            finish()
        }

        // 도장 개수 조회
        val dbHelper = ChallengeDBHelper(this)
        val stampsCollected = dbHelper.getStampsCollected("user1")

        // 저장된 도전과제 표시 (Intent로 전달된 데이터 사용)
        val challenge1 = intent.getStringExtra("challenge1")
        val challenge2 = intent.getStringExtra("challenge2")
        val challenge3 = intent.getStringExtra("challenval dbHelper\n = ChallengeDBHelper(this)\n" +
                "        val stampsCollected = dbHelper.getStampsCollected(\"user1\") // \"user1\"은 예시 사용자 이름입니다.\n" +
                "\n" +
                "        // 도장판 초기화\n" +
                "        initializeStampGrid(gridLayoutStamps, stampsCollected)ge3")

    }
    private fun changeImageButtonImage(imageButton: ImageButton) {
        // 클릭된 ImageButton의 이미지를 변경
        imageButton.setImageResource(R.drawable.dot_selected)
    }

    // 현재 진행 중인 도장판 초기화 함수
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