package com.example.guru2_android_kida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.Challenge.ChallengeList

class ChallengeCategoryActivity : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var challengeAdapter: ChallengeAdapter
    private lateinit var dbHelper: ChallengeDBHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_challenge_category, container, false)

        dbHelper = ChallengeDBHelper(requireContext())

        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        challengeAdapter = ChallengeAdapter()
        recyclerView.adapter = challengeAdapter

        // Intent로부터 카테고리 정보 가져오기
        val category = arguments?.getString("category")

        // 카테고리별 챌린지 데이터 가져오기
        val challengeList = getChallengesByCategory(category.orEmpty())

        // Adapter에 데이터 설정
        challengeAdapter.submitList(challengeList)

        return view
    }

    private fun getChallengesByCategory(category: String): List<ChallengeList> {
        val challengeList = mutableListOf<ChallengeList>()
        val db = dbHelper.readableDatabase

        // 데이터베이스에서 해당 카테고리의 챌린지를 쿼리하여 가져오는 작업을 수행합니다.
        val cursor = db.rawQuery("SELECT * FROM Challenge_List_DB WHERE 카테고리 = ?", arrayOf(category))

        while (cursor.moveToNext()) {
            val challengeName = cursor.getString(cursor.getColumnIndex("챌린지이름"))
            val challengeDescription = cursor.getString(cursor.getColumnIndex("챌린지내용"))

            challengeList.add(ChallengeList(challengeName, challengeDescription))
        }

        cursor.close()
        db.close()

        return challengeList
    }
}
