package com.example.challengeapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChallengeDetailActivity : AppCompatActivity() {

    private lateinit var textViewChallengeName: TextView
    private lateinit var listViewChallenges: ListView
    private lateinit var gridViewStamps: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        textViewChallengeName = findViewById(R.id.textViewChallengeName)
        listViewChallenges = findViewById(R.id.listViewChallenges)
        gridViewStamps = findViewById(R.id.gridViewStamps)

        //도전과제 데이터
        val challengeName = "챌린지 이름"
        val challengeList = arrayOf("도전과제 1", "도전과제 2", "도전과제 3")

        //도장판 데이터
        val stamps = Array(30) { "도장 $it" }

        //챌린지 이름
        textViewChallengeName.text = challengeName

        //도전과제 리스트에 어댑터 연결
        val adapterChallenges = ArrayAdapter(this, android.R.layout.simple_list_item_1, challengeList)
        listViewChallenges.adapter = adapterChallenges

        //도장판 GridView에 어댑터 연결
        val adapterStamps = ArrayAdapter(this, android.R.layout.simple_list_item_1, stamps)
        gridViewStamps.adapter = adapterStamps
    }
}
