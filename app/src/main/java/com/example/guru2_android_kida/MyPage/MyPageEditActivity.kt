package com.example.guru2_android_kida.MyPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.HomeDetail.ChallengeNameAdapter
import com.example.guru2_android_kida.HomeDetail.MyPageAdapter
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.ActivityMyPageEditBinding
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

class MyPageEditActivity: AppCompatActivity(), MyPageAdapter.OnChallengeClickListener {

    private lateinit var binding: ActivityMyPageEditBinding
    private lateinit var ChallengeNamedbHelper: ChallengeDBHelper
    private lateinit var challengeEditView: RecyclerView
    private lateinit var challengeNameAdapter: ChallengeNameAdapter // ChallengeNameAdapter 추가
    private lateinit var MyPageAdapter: MyPageAdapter

    private val challengeNameLIst = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // ChallengeDBHelper 초기화
        ChallengeNamedbHelper = ChallengeDBHelper(this)

        // TextView 및 Button 초기화
        challengeEditView = binding.challengeEditView

        // ChallengeNameAdapter를 적용하도록 추가
        /*challengeNameAdapter = ChallengeNameAdapter(challengeNameLIst, ChallengeNamedbHelper, this)
        challengeEditView.layoutManager = LinearLayoutManager(this)
        challengeEditView.adapter = challengeNameAdapter*/

        // MyPageAdapter를 적용하도록 추가
        MyPageAdapter = MyPageAdapter(challengeNameLIst, ChallengeNamedbHelper, this)
        challengeEditView.layoutManager = LinearLayoutManager(this)
        challengeEditView.adapter = MyPageAdapter

        // 사용자가 참여 중인 챌린지 정보 가져오기
        showChallengeList()

        binding.btnRemove.setOnClickListener {
            // MyPageFragment로 이동하는 부분
            val intent = Intent(this, MyPageFragment::class.java)
            startActivity(intent)
        }
    }

    // MyPageFragment 내에서 호출되는 함수로 TextView에 값 설정하기
    private fun getCurrentUsername(): String {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("current_username", "") ?: ""
    }

    private fun showChallengeList() {
        // ChallengeNameAdapter를 사용하여 사용자의 챌린지 목록을 가져와 화면에 표시
        val userChallenges = ChallengeNamedbHelper.getChallengesForUser(getCurrentUsername())
        // 어댑터의 내부 리스트를 업데이트하고 RecyclerView 갱신
        setChallengeList(userChallenges)

        // 텍스트 뷰에 챌린지이름을 표시
        val challengeNameTextView = findViewById<TextView>(R.id.challengeEditTextView)
        if (challengeNameLIst.isEmpty()) {
            challengeNameTextView?.text = "참여 중인 챌린지가 없습니다."
        } else {
            challengeNameTextView?.text = challengeNameLIst.joinToString("\n")
        }

        val challengeNameCheckBox = findViewById<CheckBox>(R.id.challengeNameCheckBox)
        challengeNameCheckBox?.isChecked = challengeNameLIst.isEmpty()
    }

    fun setChallengeList(newChallengeList: List<String>) {
        // 내부 리스트를 새로운 목록으로 업데이트
        challengeNameLIst.clear()
        challengeNameLIst.addAll(newChallengeList)
        // RecyclerView 갱신
        MyPageAdapter.notifyDataSetChanged()
    }

    override fun onChallengeClick(challengeName: String) {
        // 클릭 이벤트를 처리합니다.
    }
}