package com.example.guru2_android_kida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_my_page_edit : AppCompatActivity() {

    lateinit var btnRemove: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_edit)

        btnRemove = findViewById(R.id.btnRemove)

        btnRemove.setOnClickListener {
            // 선택한 챌린지 삭제

            // 페이지 이동
            var intent = Intent(this, activity_my_page::class.java)
            startActivity(intent)
        }

    }
}