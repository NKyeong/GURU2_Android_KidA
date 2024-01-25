package com.example.guru2_android_kida

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    lateinit var LoginBtn: Button
    lateinit var editId: EditText
    lateinit var editpassword: EditText
    lateinit var RegistBtn: Button

    var DB: DBHelper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DB = DBHelper(this)

        LoginBtn = findViewById(R.id.btnLogin) // 로그인 버튼
        editId = findViewById(R.id.editId) // 아이디 텍스트
        editpassword = findViewById(R.id.editPw) // 패스워드 텍스트
        RegistBtn = findViewById(R.id.btnRegister) // 회원가입 버튼

        // 로그인 버튼 클릭 시 실행되는 기능
        LoginBtn.setOnClickListener {
            val user = editId!!.text.toString()
            val pass = editpassword!!.text.toString()
            // 빈칸 제출을 했을 때 경고 메시지
            if (user == "" || pass == "") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요.",
                Toast.LENGTH_SHORT).show()
            else {
                val checkUserpass = DB!!.checkUserpass(user, pass)
                // username과 password 가 일치할때 -> 로그인 메시지, 홈 화면으로 이동
                if (checkUserpass == true) {
                    Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, HomeActivity::class.java) // 홈 화면 액티비티 넣으면 됨
                    startActivity(intent) // 로그인에 성공하면 홈 화면으로 이동
                }
                // password가 틀렸을 때 -> 경고 메시지
                else{
                    Toast.makeText(this@LoginActivity, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // 회원가입 버튼 클릭시 회원가입 화면으로 이동
        RegistBtn.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, RegisterActivity::class.java) // 회원가입 액티비티 넣으면 됨
            startActivity(loginIntent)
        }
    }
}