package com.example.guru2_android_kida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.Challenge.ChallengeList

class ChallengeCategoryActivity : AppCompatActivity() {

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
        challengeAdapter = ChallengeAdapter()
        recyclerView.adapter = challengeAdapter

        // Intent로부터 카테고리 정보 가져오기
        val category = intent.getStringExtra("category")

        // 카테고리별 챌린지 데이터 가져오기
        val challengeList = getChallengesByCategory(category.orEmpty())

        // Adapter에 데이터 설정
        challengeAdapter.submitList(challengeList)
    }

    private fun getChallengesByCategory(category: String): List<ChallengeList> {
        val challengeList = mutableListOf<ChallengeList>()
        val db = dbHelper.readableDatabase

        // 데이터베이스에서 해당 카테고리의 챌린지를 쿼리하여 가져오는 작업을 수행합니다.
        val cursor = db.rawQuery("SELECT * FROM Challenge_List_DB WHERE 카테고리 = ?", arrayOf(category))

        while (cursor.moveToNext()) {
            val challengeName = cursor.getString(cursor.getColumnIndex("챌린지 이름"))
            val challengeDescription = cursor.getString(cursor.getColumnIndex("챌린지 내용"))

            challengeList.add(ChallengeList(challengeName, challengeDescription))
        }

        cursor.close()
        db.close()

        return challengeList
    }
}