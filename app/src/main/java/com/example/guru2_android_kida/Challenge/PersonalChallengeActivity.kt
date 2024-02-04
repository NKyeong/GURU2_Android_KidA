package com.example.guru2_android_kida.Challenge

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.content.Intent
import android.view.LayoutInflater
import com.example.guru2_android_kida.HomeActivity
import com.example.guru2_android_kida.R

class PersonalChallengeActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_challenge)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnJoinChallenge = findViewById<Button>(R.id.btnJoinChallenge)
        val etChallenge1 = findViewById<EditText>(R.id.etChallenge1)
        val etChallenge2 = findViewById<EditText>(R.id.etChallenge2)
        val etChallenge3 = findViewById<EditText>(R.id.etChallenge3)

        // '뒤로 가기' 버튼 이벤트 처리
        btnBack.setOnClickListener {
            val currentChallengeName = DataManager.currentChallengeName
            currentChallengeName?.let { name ->
                val dbHelper = ChallengeDBHelper(this)
                dbHelper.deleteChallenge(currentChallengeName)
            }
            finish()
        }

        // 도전과제 입력 필드에 대한 리스너 설정
        setupChallengeInputListeners(etChallenge1, etChallenge2, etChallenge3, btnJoinChallenge)

        // 도장판 초기화
        //initializeStampGrid(gridLayoutStamps)

        //홈화면으로 이동
        fun navigateToHomeScreen() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // '챌린지 참여하기' 버튼 이벤트 처리
        btnJoinChallenge.setOnClickListener {
            val challenge1 = etChallenge1.text.toString()
            val challenge2 = etChallenge2.text.toString()
            val challenge3 = etChallenge3.text.toString()

            val dbHelper = ChallengeDBHelper(this)
            //dbHelper.saveChallengeData(challenge1, challenge2, challenge3, 0) // 초기 도장 개수 0
            navigateToHomeScreen()
        }
    }

    // 도전과제 입력 필드 리스너 설정 함수
    private fun setupChallengeInputListeners(
        et1: EditText,
        et2: EditText,
        et3: EditText,
        joinButton: Button
    ) {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 모든 도전과제 필드가 채워져 있을 때만 '챌린지 참여하기' 버튼을 활성화
                joinButton.isEnabled =
                    et1.text.isNotEmpty() && et2.text.isNotEmpty() && et3.text.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        // 각 입력 필드에 리스너 추가
        et1.addTextChangedListener(textWatcher)
        et2.addTextChangedListener(textWatcher)
        et3.addTextChangedListener(textWatcher)
    }

    // 도장판 초기화 함수
    private fun initializeStampGrid(gridLayout: GridLayout) {
        val totalStamps = 5 * 6 // 5행 6열

        for (i in 0 until totalStamps) {
            val stamp = LayoutInflater.from(this).inflate(R.layout.stamp_item, gridLayout, false)
            gridLayout.addView(stamp)

            // GridLayout 파라미터 설정
            val params = stamp.layoutParams as GridLayout.LayoutParams
            params.width = resources.getDimensionPixelSize(R.dimen.stamp_size)
            params.height = resources.getDimensionPixelSize(R.dimen.stamp_size)
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            stamp.layoutParams = params
        }
    }

    // 도전과제 데이터 저장 함수
    private fun saveChallengeData(
        challenge1: String,
        challenge2: String,
        challenge3: String,
        stampsCollected: Int
    ) {
        val dbHelper = ChallengeDBHelper(this)

        // 도전과제 정보 및 도장 개수를 ChallengeDBHelper를 통해 저장
        dbHelper.saveChallengeData(challenge1, challenge2, challenge3, stampsCollected) // 'user1'은 예시입니다.

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("challenge1", challenge1)
            put("challenge2", challenge2)
            put("challenge3", challenge3)
            put("stampsCollected", stampsCollected)
        }
        db.insert("challenges", null, values)
        db.close()
    }
}