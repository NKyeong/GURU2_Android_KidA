package com.example.guru2_android_kida

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Login.DBHelper
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

// 마이페이지 액티비티 입니다.
class MyPageFragment : Fragment(R.layout.fragment_my_page) {

    private val binding: FragmentMyPageBinding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    private lateinit var dbHelper: DBHelper
    private lateinit var userName: TextView
    private lateinit var btnEdit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding
        val view = binding.root

        binding.btnEdit.id = R.id.btnEdit
        userName = binding.userName
        btnEdit = binding.btnEdit

        // MyDB에서 특정 username 불러오기
        dbHelper = DBHelper(requireContext())

        // 특정 username을 지정하여 사용자 정보를 가져옴
        val username = 1L // 예시로 username를 1로 설정
        val user = dbHelper.getUser(username)

        if (user != null) {
            // 사용자 정보를 사용하여 원하는 작업 수행
            // 예시로 로그 출력
            userName.text = user.username}


        // 레벨 불러오기



        // challenge 데이터베이스에서 진행중인 챌린지 불러오기
        // 챌린지 정보를 담을 모델 클래스 만들기
        // 연결된 dbHelper는 login에 대한 DB라서 challenge에 관한 databaseHelper를 만들어야함
        // 추가로 어댑터도 만들어야함
        val challengeView: RecyclerView = binding.challengeView
        challengeView.layoutManager = LinearLayoutManager(requireContext())

        val challengeList = dbHelper.getChallenges()

        val adapter = ChallengeAdapter(challengeList)
        challengeView.adapter = adapter


        // 수정하는 페이지(activity_my_page_edit)로 이동
        btnEdit.setOnClickListener {
            var intent = Intent(requireContext(), MyPageEditFragment::class.java)
            startActivity(intent)
        }

        return view
    }


    // 다른 페이지들과 연결
    // fragment_my_page에서 네비게이션 바 생성하고 연결하기
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_page, menu)
        return true
    }*/
}