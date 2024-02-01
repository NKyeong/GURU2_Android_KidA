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
import com.example.guru2_android_kida.HomeDetail.ChallengeNameAdapter
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
    private lateinit var challengeNameAdapter: ChallengeNameAdapter // ChallengeNameAdapter 추가

    private val challengeNameLIst = mutableListOf<String>() // 챌린지 정보 리스트 추가


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

        // ChallengeNameAdapter 초기화
        //challengeNameAdapter = ChallengeNameAdapter()

        // DB에서 특정 username 불러오기
        // DBHelper 초기화
        loginDBHelper = DBHelper(requireContext())
        loginDBHelper.onCreate(loginDBHelper.writableDatabase)

        val username = "desiredUsername"
        userName.text = loginDBHelper.onLoginSuccess(username).toString()

        // 레벨 불러오기


        // 사용자가 참여 중인 챌린지 정보 가져오기
        showChallengeList()


        // 수정하는 페이지(activity_my_page_edit)로 이동
        binding.btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), MyPageEditActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun showChallengeList() {
        // 현재 로그인한 사용자의 이름을 가져오는 메서드 (적절한 메서드로 대체)

        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_username", "") ?: ""

        // ChallengeNameAdapter를 사용하여 사용자의 챌린지 목록을 가져와 화면에 표시
        challengeNameAdapter.setChallengeListForUser(currentUsername)

        // 텍스트 뷰에 챌린지이름을 표시
        val challengeNameTextView = view?.findViewById<TextView>(R.id.challengeNameTextView)
        if (challengeNameAdapter.itemCount == 0) {
            challengeNameTextView?.text = "참여 중인 챌린지가 없습니다."
        } else {
            challengeNameTextView?.text = challengeNameLIst.joinToString("\n")
        }
    }
}