package com.example.guru2_android_kida

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// 마이페이지 액티비티 입니다.
class MyPageFragment : Fragment(R.layout.fragment_my_page) {

    lateinit var btnEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_page)

        btnEdit = findViewById(R.id.btnEdit)

        // 닉네임 불러오기

        // 레벨 불러오기

        // 진행중인 챌린지 불러오기

        // 수정하는 페이지(activity_my_page_edit)로 이동
        btnEdit.setOnClickListener {
            var intent = Intent(this, MyPageEditFragment::class.java)
            startActivity(intent)
        }

    }

    // 옵션메뉴 제작
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_page, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            // 홈으로 이동
            R.id.home_id -> {
                val intent = Intent(this, activity 이름::class.java)
                startActivity(intent)
                return true
            }

            // 챌린지 만드는 페이지로 이동
            R.id.make_challenge_id -> {
                val intent = Intent(this, activity 이름::class.java)
                startActivity(intent)
                return true
            }
        }
    }*/
}