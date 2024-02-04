package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        challengeAdapter = ChallengeAdapter(this)
        recyclerView.adapter = challengeAdapter

        val category = intent.getStringExtra("category")
        val challengeList = getChallengesByCategory(category.orEmpty())

        challengeAdapter.submitList(challengeList)
    }

    @SuppressLint("Range")
    private fun getChallengesByCategory(category: String): List<ChallengeList> {
        val challengeList = mutableListOf<ChallengeList>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Challenge_List_DB WHERE 카테고리 = ?", arrayOf(category))

        while (cursor.moveToNext()) {
            val challengeName = cursor.getString(cursor.getColumnIndex("챌린지 이름"))
            val challengeDescription = cursor.getString(cursor.getColumnIndex("챌린지 내용"))
            val imageName = cursor.getString(cursor.getColumnIndex("이미지 이름"))

            challengeList.add(ChallengeList(challengeName, challengeDescription, imageName))
        }

        cursor.close()
        db.close()

        return challengeList
    }

    override fun onChallengeStartClicked(challengeName: String, position: Int,  challengeList: List<ChallengeList> ) {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_username", "") ?: ""
        val challengeDBHelper = ChallengeDBHelper(this)

        challengeDBHelper.updateUserChallengeInfo(currentUsername, challengeName)
    }

}
