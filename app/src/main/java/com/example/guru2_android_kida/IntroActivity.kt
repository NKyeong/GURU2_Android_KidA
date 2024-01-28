package com.example.guru2_android_kida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.guru2_android_kida.Login.LoginActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // 인트로 화면이 3초동안 보이며, 그 다음 메인 엑티비티가 실행되도록 하는 코드입니다.
        // 인트로 화면 다음에 보일 화면(로그인 화면)으로 코드를 수정해야합니다.
        // 매니페스트 파일에서 가장 먼저 실행되는 파일을 바꾸어야 합니다.
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}