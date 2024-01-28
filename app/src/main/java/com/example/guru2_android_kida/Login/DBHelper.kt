package com.example.guru2_android_kida.Login

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "Login.db", null, 1) {
    // 로그인 DB 테이블을 생성
    override fun onCreate(MyDB: SQLiteDatabase?) {
        MyDB!!.execSQL("CREATE TABLE users(username text PRIMARY KEY, password text)")
    }
    // 로그인 DB 테이블을 갱신
    override fun onUpgrade(MyDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        MyDB!!.execSQL("DROP TABLE IF EXISTS user")
    }
    // username과 password 정보를 받아 DB에 삽입, 삽입이 성공하면 true, 실패하면 false를 리턴
    fun insertData(username: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        return if (result == -1L) false else true
    }
    // 사용자 입력을 감지해 입력이 없을 경우 false를 아닐 경우 true를 리턴
    fun checkUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        var res = true
        val cursor = MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        if (cursor.count <= 0) res = false
        return res
    }
    // username이 존재하는지 그리고 password가 입력되었는지 확인. 입력이 안되었을 경우 false를 리턴
    fun checkUserpass(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        var res = true
        val cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",
            arrayOf(username, password)
        )
        if (cursor.count <= 0) res = false
        return res
    }

    companion object {
        // db 이름을 "Login.db"로 설정
        const val DBNAME = "Login.db"
    }
}