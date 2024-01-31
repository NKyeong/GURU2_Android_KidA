package com.example.guru2_android_kida.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private val binding: FragmentMyPageBinding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        editButton = binding.editButton

        editButton.setOnClickListener {
            val intent = Intent(this, MyPageEditActivity::class.java)
            startActivity(intent)
        }
    }
}