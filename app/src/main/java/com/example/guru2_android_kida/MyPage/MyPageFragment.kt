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
        userName = binding.userName

        /*// TextView 및 Button 초기화

        //btnEdit = binding.btnEdit
        challengeView = binding.challengeView

        // ChallengeDBHelper 초기화
        challengeDBHelper = ChallengeDBHelper(requireContext())

        // ChallengeNameAdapter 초기화
        //challengeNameAdapter = ChallengeNameAdapter()

        // DB에서 특정 username 불러오기
        // DBHelper 초기화
        loginDBHelper = DBHelper(requireContext())
        loginDBHelper.onCreate(loginDBHelper.writableDatabase)*/


        // 레벨 불러오기


        // 사용자가 참여 중인 챌린지 정보 가져오기
        // SharedPreferences에서 값을 가져와서 TextView에 설정
        setUserNameInView()


        // 수정하는 페이지(activity_my_page_edit)로 이동
        binding.btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), MyPageEditActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    //// MyPageFragment 내에서 호출되는 함수로 TextView에 값 설정하기
    private fun setUserNameInView() {
        val username = getCurrentUsername()
        userName.text = username
    }
    //SharedPreferences 값 가져오기
    private fun getCurrentUsername(): String {
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("current_username", "") ?: ""
    }
}