package com.example.guru2_android_kida.Ranking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guru2_android_kida.databinding.FragmentRankingBinding
import java.text.SimpleDateFormat
import java.util.*

class RankingFragment : Fragment() {

    private lateinit var binding: FragmentRankingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        binding.recyclerViewRanking.layoutManager = LinearLayoutManager(context) // 여기에 추가
        updateCurrentMonth()
        setupRankingList()
        return binding.root
    }

    private fun updateCurrentMonth() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val lastMonth = SimpleDateFormat("MM", Locale.getDefault()).format(calendar.time)
        binding.tvCurrentMonth.text = "#${lastMonth}월의 랭킹"
    }

    private fun setupRankingList() {
        val random = Random()

        // 랜덤한 이름과 도장 개수를 가진 유저 데이터 생성
        val rankingData = List(50) {
            UserRanking("User ${random.nextInt(100)}", (1..100).random())
        }

        // 도장 개수에 따라 내림차순으로 정렬
        val sortedRankingData = rankingData.sortedByDescending { it.stampsCount }

        // 어댑터에 정렬된 데이터 전달
        val adapter = UserRankingAdapter(sortedRankingData)
        binding.recyclerViewRanking.adapter = adapter

        // 로그로 데이터 크기 및 어댑터 설정 확인
        Log.d("RankingFragment", "Ranking data size: ${rankingData.size}")
        Log.d("RankingFragment", "Adapter is set")
    }
}
