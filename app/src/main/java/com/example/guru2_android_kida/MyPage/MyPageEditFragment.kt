package com.example.guru2_android_kida.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeAdapter
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.FragmentMyPageEditBinding

class MyPageEditFragment : AppCompatActivity() {

    private val binding: FragmentMyPageEditBinding by lazy {
        FragmentMyPageEditBinding.inflate(layoutInflater)
    }

    private lateinit var challengeDBHelper: ChallengeDBHelper
    private lateinit var adapter: ChallengeAdapter
    private lateinit var btnRemove: Button

    // 가상의 챌린지 ID(1), 실제로는 클릭된 아이템의 ID를 여기에 설정해야 함
    private val selectedChallengeId: Long = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_page_edit)

        challengeDBHelper = ChallengeDBHelper(this)
        binding.btnRemove.id = R.id.btnRemove


        /*// challengeView 초기화
        val challengeView: RecyclerView = binding.challengeView

        // 어댑터 초기화
        adapter = ChallengeAdapter(emptyList())
        challengeView.adapter = adapter*/

        // 삭제 버튼 클릭 시 선택한 챌린지 삭제
        btnRemove.setOnClickListener {
            /*val selectedChallenges = ChallengeAdapter.getSelectedChallenges()

            if (selectedChallenges.isNotEmpty()) {
                // ChallengeDBHelper를 통해 선택한 챌린지 삭제
                val success = challengeDBHelper.removeChallenges(selectedChallenges)

                if (success) {
                    // 성공적으로 삭제되면 어댑터 갱신
                    val userChallenges = challengeDBHelper.getJoinedChallengesForUser(username)
                    adapter.submitList(userChallenges)
                    // 또는 adapter.notifyDataSetChanged() 호출
                } else {
                    // 오류 처리
                }
            }*/

            // 페이지 이동
            var intent = Intent(this, MyPageFragment::class.java)
            startActivity(intent)
        }
    }
}