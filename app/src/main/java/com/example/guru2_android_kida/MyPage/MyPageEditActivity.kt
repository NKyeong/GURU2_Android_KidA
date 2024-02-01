package com.example.guru2_android_kida.MyPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.ActivityMyPageEditBinding
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

class MyPageEditActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyPageEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageEditBinding.inflate(layoutInflater)

        val view = binding.root

        binding.btnRemove.setOnClickListener {
            // MyPageFragment로 이동하는 부분
            val intent = Intent(this, MyPageFragment::class.java)
            startActivity(intent)
        }

        setContentView(view)

    }
}