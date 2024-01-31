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

        val username = intent.getStringExtra("username")
        val challengeName = intent.getStringExtra("challengeName")

        // 사용자 이름이나 챌린지 이름이 전달되지 않았다면 활동 종료
        if (username == null || challengeName == null) {
            finish()
            return
        }

        val dbHelper = ChallengeDBHelper(this)

        val challengeInfo = dbHelper.getChallengeInfo(username, challengeName)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val gridLayoutStamps = findViewById<GridLayout>(R.id.gridLayoutStamps)
        val tvChallenge1 = findViewById<TextView>(R.id.tvChallenge1)
        val tvChallenge2 = findViewById<TextView>(R.id.tvChallenge2)
        val tvChallenge3 = findViewById<TextView>(R.id.tvChallenge3)

        if (challengeInfo != null) {
            tvChallenge1.text = challengeInfo.challenge1
            tvChallenge2.text = challengeInfo.challenge2
            tvChallenge3.text = challengeInfo.challenge3
        }

        btnBack.setOnClickListener {
            finish()
        }

        if (challengeInfo != null) {
            initializeStampGrid(gridLayoutStamps, challengeInfo.stampsCollected)
        }
    }

    private fun initializeStampGrid(gridLayout: GridLayout, stampsCollected: Int) {
        val totalStamps = 5 * 6

        for (i in 0 until totalStamps) {
            val stamp = ImageView(this)
            if (i < stampsCollected) {
                stamp.setImageResource(R.drawable.indicator_dot_selected)
            } else {
                stamp.setImageResource(R.drawable.indicator_dot_unselected)
            }
            gridLayout.addView(stamp)

            val params = stamp.layoutParams as GridLayout.LayoutParams
            params.width = 0
            params.height = 0
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            stamp.layoutParams = params
        }
    }
}
