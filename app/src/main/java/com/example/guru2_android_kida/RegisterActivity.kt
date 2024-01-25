package com.example.guru2_android_kida

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {
    lateinit var editRegistName: EditText
    lateinit var editRegistPw: EditText
    lateinit var editRegistRePw: EditText
    lateinit var btnInputRegist: Button

    var DB: DBHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        DB = DBHelper(this)

        editRegistName = findViewById(R.id.editRegistName) // 회원가입 닉네임 텍스트
        editRegistPw = findViewById(R.id.editRegistPw) // 회원가입 패스워드 텍스트
        editRegistRePw = findViewById(R.id.editRegistRePw) // 회원가입 패스워드 재입력 텍스트
        btnInputRegist = findViewById(R.id.btnInputRegist) // 회원가입 완료 버튼

        // 회원가입 완료 버튼을 눌렀을때 일어나는 기능
        btnInputRegist.setOnClickListener {
            val user = editRegistName.text.toString()
            val pass = editRegistPw.text.toString()
            val repass = editRegistRePw.text.toString()
            // 빈칸이 있는 상태로 제출 했을때
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@RegisterActivity,
                "회원정보를 전부 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) {
                    val checkUsername = DB!!.checkUsername(user)
                    // password와 repassword가 일치하고, username이 db에 없는 경우 db에 회원정보 추가 후 메시지
                    if (checkUsername == false) {
                        val insert = DB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "가입되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent) // 로그인 화면으로 이동
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "오류가 발생하였습니다. 다시 입력해주세요.",
                                Toast.LENGTH_SHORT
                            ).show() // insert가 제대로 되지 않았을 경우, 나오는 메시지
                        }
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "이미 가입된 회원의 이름입니다. 다른 이름을 입력해주세요.",
                            Toast.LENGTH_SHORT
                        ).show() // username이 이미 db안에 있을 경우, 이미 가입된 회원임을 알리는 메시지
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "비밀번호가 일치하지 않습니다.",
                        Toast.LENGTH_SHORT
                    ).show() // 패스워드와 패스워드 재입력이 일치하지 않는 경우 나오는 메시지
                }
            }
        }
    }
}