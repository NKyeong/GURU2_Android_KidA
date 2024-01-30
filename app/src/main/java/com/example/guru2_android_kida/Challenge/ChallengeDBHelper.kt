package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ChallengeDBHelper(context: Context) :
    SQLiteOpenHelper(context, "Challenge_List_DB.db", null, 1) {

    override fun onCreate(challengedb: SQLiteDatabase?) {
        // Challenge_List_DB 테이블 생성
        //challengedb?.execSQL("CREATE TABLE Challenge_List_DB(카테고리 text, 챌린지이름 text, 챌린지내용 text)")
        // 사용자 챌린지 정보를 저장할 테이블 생성
        challengedb?.execSQL("CREATE TABLE User_Challenge_Info(username text, 챌린지이름 text)")
    }

    // 사용자가 챌린지에 참여하는 메서드
    fun joinChallenge(username: String, challengeName: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("username", username)
            put("챌린지이름", challengeName)
        }

        val result = db.insert("User_Challenge_Info", null, contentValues)
        db.close()

        return result != -1L

    }

    @SuppressLint("Range")
    // 사용자의 챌린지 정보를 가져오는 메서드
    fun getChallengesForUser(username: String): List<String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT 챌린지이름 FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        val challenges = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val challenge = cursor.getString(cursor.getColumnIndex("챌린지이름"))
            challenges.add(challenge)
        }

        cursor.close()
        db.close()

        return challenges
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // User_Challenge_Info 테이블에 해당 username이 이미 존재하는지 확인하는 메서드
    fun isUsernameExists(username: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
    // User_Challenge_Info 테이블에 username을 추가하는 메서드
    fun addUsernameToUserChallengeInfo(username: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("username", username)
        }
        // 사용자의 username을 User_Challenge_Info 테이블에 추가
        val result = db.insert("User_Challenge_Info", null, contentValues)

        db.close()

        // 추가에 성공했는지 여부를 반환
        return result != -1L
    }


    @SuppressLint("Range")
    // 사용자가 참여 중인 챌린지이름을 가져오는 메서드
    fun getJoinedChallengesForUser(username: String): List<String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT 챌린지이름 FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        val challenges = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val challenge = cursor.getString(cursor.getColumnIndex("챌린지이름"))
            challenges.add(challenge)
        }

        cursor.close()
        db.close()

        return challenges
    }

}