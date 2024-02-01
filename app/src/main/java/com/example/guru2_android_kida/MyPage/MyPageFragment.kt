package com.example.guru2_android_kida.MyPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeAdapter
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.Login.DBHelper
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

// 마이페이지 액티비티 입니다.
class MyPageFragment : Fragment(R.layout.fragment_my_page) {

    private val binding: FragmentMyPageBinding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    private lateinit var challengeDBHelper: ChallengeDBHelper
    private lateinit var loginDBHelper: DBHelper
    private lateinit var userName: TextView
    //private lateinit var btnEdit: Button
    private lateinit var challengeView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = binding.root

        // TextView 및 Button 초기화
        userName = binding.userName
        //btnEdit = binding.btnEdit
        challengeView = binding.challengeView

        // ChallengeDBHelper 초기화
        challengeDBHelper = ChallengeDBHelper(requireContext())

        // DB에서 특정 username 불러오기
        /*val username = "desiredUsername"  // 사용자 이름을 임의로 지정
        userName.text = challengeDBHelper.getUsername(username)  // 특정 사용자의 이름을 표시*/

        val username = "desiredUsername"
        userName.text = loginDBHelper.onLoginSuccess(username).toString()

        // 레벨 불러오기



        // ChallengeDBHelper에서 진행중인 챌린지 불러오기
        // 사용자가 참여 중인 챌린지 정보 가져오기
        val userChallenges = challengeDBHelper.getChallengesForUser(username)

        // RecyclerView에 적용할 어댑터 생성
        /*val adapter = ChallengeAdapter(userChallenges)

        // RecyclerView 설정
        challengeView.layoutManager = LinearLayoutManager(requireContext())
        challengeView.adapter = adapter*/


        // 수정하는 페이지(activity_my_page_edit)로 이동
        binding.btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), MyPageEditActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}