package com.example.guru2_android_kida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.challengeapp.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private lateinit var binding: FragmentRankingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 가상의 랭킹 데이터 생성
        val rankingData = listOf(
            "User 1",
            "User 2",
            "User 3",
            "User 4",
            "User 5"
        )

        // 어댑터를 통해 데이터를 리스트뷰에 연결
        val adapter = RankingAdapter(requireContext(), rankingData)
        binding.listViewRanking.adapter = adapter
    }
}
