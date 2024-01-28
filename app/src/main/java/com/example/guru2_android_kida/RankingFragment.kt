package com.example.guru2_android_kida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import com.example.guru2_android_kida.databinding.FragmentRankingBinding
import java.util.Calendar
import java.util.Locale

class RankingFragment : Fragment() {

    // FragmentRankingBinding을 사용하여 뷰 바인딩 초기화
    private val binding: FragmentRankingBinding by lazy {
        FragmentRankingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateCurrentMonth()
        setupRankingList()
        return binding.root
    }

    // 전 월을 업데이트하는 함수
    private fun updateCurrentMonth() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val lastMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
        binding.tvCurrentMonth.text = "#${lastMonth}의 랭킹"
    }

    // 랭킹 리스트를 설정하는 함수
    private fun setupRankingList() {
        // 랜덤 사용자 데이터 생성
        val rankingData = List(50) { UserRanking("User ${it + 1}", Random().nextInt(100)) }
        // 도장 개수에 따라 내림차순으로 정렬
        val sortedRankingData = rankingData.sortedByDescending { it.stampsCount }
        val adapter = UserRankingAdapter(sortedRankingData)
        binding.recyclerViewRanking.adapter = adapter
    }
}
