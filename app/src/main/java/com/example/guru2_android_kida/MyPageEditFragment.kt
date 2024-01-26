package com.example.guru2_android_kida

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyPageEditFragment : AppCompatActivity() {

    lateinit var btnRemove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_page_edit)

        btnRemove = findViewById(R.id.btnRemove)

        btnRemove.setOnClickListener {
            // 선택한 챌린지 삭제

            // 페이지 이동
            var intent = Intent(this, MyPageFragment::class.java)
            startActivity(intent)
        }
    }
}