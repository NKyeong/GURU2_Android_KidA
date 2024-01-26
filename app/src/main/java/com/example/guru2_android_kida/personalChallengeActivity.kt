package com.example.challengeapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class PersonalChallengeActivity : AppCompatActivity() {

    private lateinit var editTextChallenge1: EditText
    private lateinit var editTextChallenge2: EditText
    private lateinit var editTextChallenge3: EditText
    private lateinit var buttonSaveChallenge: Button
    private lateinit var gridViewStamps: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_challenge)

        editTextChallenge1 = findViewById(R.id.editTextChallenge1)
        editTextChallenge2 = findViewById(R.id.editTextChallenge2)
        editTextChallenge3 = findViewById(R.id.editTextChallenge3)
        buttonSaveChallenge = findViewById(R.id.buttonSaveChallenge)
        gridViewStamps = findViewById(R.id.gridViewStamps)

        // 빈 도장판 30개 데이터 생성 (이미지 등의 데이터는 실제로 사용할 데이터로 변경 필요)
        val stamps = Array(30) { "도장 $it" }

        // GridView에 어댑터 연결
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stamps)
        gridViewStamps.adapter = adapter

        // '챌린지 참여하기' 버튼 클릭 시 저장 기능 추가
        buttonSaveChallenge.setOnClickListener {
            saveChallenge()
        }
    }

    private fun saveChallenge() {
        // TODO: 도전과제 및 도장판 데이터를 저장하는 로직 추가
        // editTextChallenge1.text.toString(), editTextChallenge2.text.toString(), editTextChallenge3.text.toString() 등 활용
    }
}
