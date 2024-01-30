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

    private fun getRandomNickname(): String {
        val adjectives = listOf("지적인", "분석적인", "창의적인", "호기심 많은", "통찰력 있는", "열정적인", "논리적인", "직관적인", "혁신적인", "천재적인", "효율적인", "체계적인", "정확한", "꼼꼼한", "예리한", "독창적인", "카리스마 있는", "도전적인", "용감한", "영감을 주는", "영리한", "기발한", "명민한", "예측 가능한", "선구적인", "발명적인", "감각적인", "간결한", "민첩한", "신중한", "신속한", "생산적인", "사려깊은", "단호한", "놀라운", "다재다능한", "확신있는", "탁월한", "자신감있는", "차분한", "포용력있는", "정직한", "헌신적인", "현명한", "협력적인", "화려한", "활발한", "끈기있는", "경험 많은", "자연친화적인")
        val nouns = listOf("분석가", "통계학자", "연구원", "과학자", "천재", "예측가", "전략가", "해커", "발명가", "모델러", "데이터러버", "데이터사이언티스트", "최적화전문가", "컴퓨터마스터", "네트워커", "연산자", "알고리즘", "프로그래머", "데이터엔지니어", "디자이너", "빅데이터전문가", "인공지능", "머신러닝연구원", "딥러닝전문가", "로봇공학자", "시스템엔지니어", "네트워크분석가", "소프트웨어개발자", "하드웨어기술자", "정보보안전문가", "데이터베이스관리자", "클라우드전문가", "사이버보안전문가", "UI/UX디자이너", "웹개발자", "앱개발자", "시스템아키텍트", "게임개발자", "멀티미디어디자이너", "컨텐츠제작자", "블록체인전문가", "가상현실개발자", "증강현실전문가", "드론조종사", "빅데이터애널리스트", "사물인터넷전문가", "컴퓨터비전엔지니어", "스토리텔러", "디지털마케터", "사이버보안연구원")
        return "${adjectives.random()} ${nouns.random()}"
    }

    private fun setupRankingList() {
        val random = Random()

        // 랜덤한 이름과 도장 개수를 가진 유저 데이터 생성
        val rankingData = List(50) {
            UserRanking(getRandomNickname(), (1..50).random())
        }

        // 데이터 정렬 확인
        val sortedRankingData = rankingData.sortedByDescending { it.stampsCount }

        // 어댑터에 정렬된 데이터 전달
        val adapter = UserRankingAdapter(sortedRankingData)
        binding.recyclerViewRanking.adapter = adapter

        // 로그로 데이터 크기 및 어댑터 설정 확인
        Log.d("RankingFragment", "Ranking data size: ${rankingData.size}")
        Log.d("RankingFragment", "Adapter is set")
    }
}
