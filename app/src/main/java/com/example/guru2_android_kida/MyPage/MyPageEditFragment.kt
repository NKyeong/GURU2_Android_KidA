package com.example.guru2_android_kida.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2_android_kida.Login.DBHelper
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.FragmentMyPageEditBinding

class MyPageEditFragment : AppCompatActivity() {

    private val binding: FragmentMyPageEditBinding by lazy {
        FragmentMyPageEditBinding.inflate(layoutInflater)
    }

    lateinit var btnRemove: Button
    lateinit var dbHelper: DBHelper

    // 가상의 챌린지 ID(1), 실제로는 클릭된 아이템의 ID를 여기에 설정해야 함
    private val selectedChallengeId: Long = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_page_edit)

        dbHelper = DBHelper(this)
        binding.btnRemove.id = R.id.btnRemove

        btnRemove.setOnClickListener {
            // 선택한 챌린지 삭제
            deleteSelectedChallenge(selectedChallengeId)

            // 페이지 이동
            var intent = Intent(this, MyPageFragment::class.java)
            startActivity(intent)
        }
    }

    // 선택한 챌린지를 삭제하는 함수
    private fun deleteSelectedChallenge(challengeId: Long) {
        // 이 부분에 선택한 챌린지를 삭제하는 코드를 추가
        //dbHelper.deleteChallenge(challengeId)
    }
}