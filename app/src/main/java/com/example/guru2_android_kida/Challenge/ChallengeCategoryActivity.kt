package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.Challenge.ChallengeList
import com.example.guru2_android_kida.R

class ChallengeCategoryActivity : AppCompatActivity(), ChallengeItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var challengeAdapter: ChallengeAdapter
    private lateinit var dbHelper: ChallengeDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_challenge_category)

        dbHelper = ChallengeDBHelper(this)

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        challengeAdapter = ChallengeAdapter(this)
        recyclerView.adapter = challengeAdapter

        // Intent로부터 카테고리 정보 가져오기
        val category = intent.getStringExtra("category")

        // 카테고리별 챌린지 데이터 가져오기
        val challengeList = getChallengesByCategory(category.orEmpty())

        // Adapter에 데이터 설정
        challengeAdapter.submitList(challengeList)
    }

    @SuppressLint("Range")
    private fun getChallengesByCategory(category: String): List<ChallengeList> {
        val challengeList = mutableListOf<ChallengeList>()
        val db = dbHelper.readableDatabase

        // 데이터베이스에서 해당 카테고리의 챌린지를 쿼리하여 가져오는 작업을 수행합니다.
        val cursor = db.rawQuery("SELECT * FROM Challenge_List_DB WHERE 카테고리 = ?", arrayOf(category))

        while (cursor.moveToNext()) {
            val challengeName = cursor.getString(cursor.getColumnIndex("챌린지 이름"))
            val challengeDescription = cursor.getString(cursor.getColumnIndex("챌린지 내용"))
            val imageResourceId = cursor.getString(cursor.getColumnIndex("이미지 리소스 ID"))

            challengeList.add(ChallengeList(challengeName, challengeDescription, imageResourceId))
        }

        cursor.close()
        db.close()

        return challengeList
    }

    // ChallengeItemClickListener의 메서드 구현
    override fun onChallengeStartClicked(challengeName: String) {
        // 현재 로그인한 사용자의 이름을 가져오기
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_username", "") ?: ""
        // User_Challenge_Info 테이블에 챌린지 이름 추가 또는 업데이트
        val challengeDBHelper = ChallengeDBHelper(this)
        challengeDBHelper.updateUserChallengeInfo(currentUsername, challengeName)
    }
}
