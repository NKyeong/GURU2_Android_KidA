package com.example.guru2_android_kida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class activity_my_page : AppCompatActivity() {

    lateinit var btnEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        btnEdit = findViewById(R.id.btnEdit)

        // 닉네임 불러오기

        // 레벨 불러오기

        // 진행중인 챌린지 불러오기

        // 수정하는 페이지(activity_my_page_edit)로 이동
        btnEdit.setOnClickListener {
            var intent = Intent(this, activity_my_page_edit::class.java)
            startActivity(intent)
        }

    }

    // 옵션메뉴 제작
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_page, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            // 홈으로 이동
            R.id."home_id" -> {
                val intent = Intent(this, "activity 이름"::class.java)
                startActivity(intent)
                return true
            }

            // 챌린지 만드는 페이지로 이동
            R.id."make_challenge_id" -> {
                val intent = Intent(this, "activity 이름"::class.java)
                startActivity(intent)
                return true
            }
        }
    }
}