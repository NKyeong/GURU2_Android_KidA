package com.example.guru2_android_kida.MyPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.HomeDetail.ChallengeNameAdapter
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

// 마이페이지 액티비티 입니다.
class MyPageFragment : Fragment(R.layout.fragment_my_page), ChallengeNameAdapter.OnChallengeClickListener {

    private val binding: FragmentMyPageBinding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    private lateinit var ChallengeNamedbHelper: ChallengeDBHelper
    private lateinit var userName: TextView
    private lateinit var challengeView: RecyclerView
    private lateinit var challengeNameAdapter: ChallengeNameAdapter // ChallengeNameAdapter 추가

    private val challengeNameLIst = mutableListOf<String>() // 챌린지 정보 리스트 추가


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = binding.root
        userName = binding.userName

        // ChallengeDBHelper 초기화
        ChallengeNamedbHelper = ChallengeDBHelper(requireContext())

        // TextView 및 Button 초기화
        challengeView = binding.challengeView

        // ChallengeNameAdapter를 적용하도록 추가
        challengeNameAdapter = ChallengeNameAdapter(challengeNameLIst, ChallengeNamedbHelper, this)
        challengeView.layoutManager = LinearLayoutManager(requireContext())
        challengeView.adapter = challengeNameAdapter

        // username 불러오기
        setUserNameInView()

        // 레벨 불러오기

        // 사용자가 참여 중인 챌린지 정보 가져오기
        showChallengeList()

        // 수정하는 페이지(activity_my_page_edit)로 이동
        binding.btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), MyPageEditActivity::class.java)
            startActivity(intent)
        }

        // 내 정보 설정 화면으로 이동
        binding.myPageInfo.setOnClickListener {
            val intent = Intent(requireContext(), MyPageInfoActivity::class.java)
            startActivity(intent)
        }

        // 알림 설정 화면으로 이동
        binding.myPageAlarm.setOnClickListener {
            val intent = Intent(requireContext(), MyPageAlarmActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    // MyPageFragment 내에서 호출되는 함수로 TextView에 값 설정하기
    private fun setUserNameInView() {
        val username = getCurrentUsername()
        userName.text = username
    }

    //SharedPreferences 값 가져오기
    private fun getCurrentUsername(): String {
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("current_username", "") ?: ""
    }

    private fun showChallengeList() {
        // ChallengeNameAdapter를 사용하여 사용자의 챌린지 목록을 가져와 화면에 표시
        val userChallenges = ChallengeNamedbHelper.getChallengesForUser(getCurrentUsername())
        // 어댑터의 내부 리스트를 업데이트하고 RecyclerView 갱신
        setChallengeList(userChallenges)

        // 텍스트 뷰에 챌린지이름을 표시
        val challengeNameTextView = view?.findViewById<TextView>(R.id.challengeNameTextView)
        if (challengeNameLIst.isEmpty()) {
            challengeNameTextView?.text = "참여 중인 챌린지가 없습니다."
        } else {
            challengeNameTextView?.text = challengeNameLIst.joinToString("\n")
        }
    }

    fun setChallengeList(newChallengeList: List<String>) {
        // 내부 리스트를 새로운 목록으로 업데이트
        challengeNameLIst.clear()
        challengeNameLIst.addAll(newChallengeList)
    }

    override fun onChallengeClick(challengeName: String) {
        // 클릭 이벤트를 처리합니다.
    }
}