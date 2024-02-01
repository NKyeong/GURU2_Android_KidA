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
import com.example.guru2_android_kida.databinding.FragmentMyPageBinding

class MyPageEditActivity: Fragment(R.layout.activity_my_page_edit) {

    private val binding: FragmentMyPageBinding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = binding.root

        /*binding.btnRemove.setOnClickListener {
            val intent = Intent(requireContext(), MyPageFragment::class.java)
            startActivity(intent)
        }*/

        return view

    }
}