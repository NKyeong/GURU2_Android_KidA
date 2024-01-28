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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.recyclerView

// 마이페이지 액티비티 입니다.
class MyPageFragment : Fragment(R.layout.fragment_my_page) {
    private lateinit var dbHelper: DBHelper
    private lateinit var userName: TextView

    lateinit var btnEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_page)

        btnEdit = findViewById(R.id.btnEdit)

        // MyDB에서 특정 username 불러오기
        dbHelper = DBHelper(this)

        userName = findViewById(R.id.userName)

        // 특정 username을 지정하여 사용자 정보를 가져옴
        val username = 1L // 예시로 username를 1로 설정
        val user = dbHelper.getUser(username)

        if (user != null) {
            // 사용자 정보를 사용하여 원하는 작업 수행
            // 예시로 로그 출력
            userName.text = MyDB.username}


        // 레벨 불러오기



        // challenge 데이터베이스에서 진행중인 챌린지 불러오기
        // 챌린지 정보를 담을 모델 클래스 만들기
        // 연결된 dbHelper는 login에 대한 DB라서 challenge에 관한 databaseHelper를 만들어야함
        // 추가로 어댑터도 만들어야함
        val challengeView: RecyclerView = findViewById(R.id.challengeView)
        challengeView.layoutManager = LinearLayoutManager(this)

        dbHelper = DBHelper(this)
        val challengeList = dbHelper.getChallenges()

        val adapter = ChallengeAdapter(challengeList)
        challengeView.adapter = adapter


        // 수정하는 페이지(activity_my_page_edit)로 이동
        btnEdit.setOnClickListener {
            var intent = Intent(this, MyPageEditFragment::class.java)
            startActivity(intent)
        }

    }


    // 다른 페이지들과 연결
    // fragment_my_page에서 네비게이션 바 생성하고 연결하기
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_page, menu)
        return true
    }*/
}