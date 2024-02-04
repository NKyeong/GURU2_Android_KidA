package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChallengeDBHelper(context: Context) :
    SQLiteOpenHelper(context, "Challenge_List_DB.db", null, 2) {

    override fun onCreate(challengedb: SQLiteDatabase?) {
        challengedb?.execSQL(
            "CREATE TABLE IF NOT EXISTS User_Challenge_Info(" +
                    "username text, " +
                    "챌린지이름 text, " +
                    "challenge1 TEXT, " +
                    "challenge2 TEXT, " +
                    "challenge3 TEXT, " +
                    "stampsCollected INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User_Challenge_Info")
        onCreate(db)
    }

    @SuppressLint("Range")
    // 사용자의 챌린지 정보를 가져오는 메서드
    fun getChallengesForUser(username: String): List<String> {
        val challengeNameLIst = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT 챌린지이름 FROM User_Challenge_Info WHERE username = ? AND 챌린지이름 IS NOT NULL", arrayOf(username))

        while (cursor.moveToNext()) {
            val challengeName = cursor.getString(cursor.getColumnIndex("챌린지이름"))
            challengeNameLIst.add(challengeName)
        }

        cursor.close()
        db.close()

        return challengeNameLIst
    }

    // 도전과제 데이터 및 도장 개수 저장
    fun saveChallengeData(challenge1: String, challenge2: String, challenge3: String, stampsCollected: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("challenge1", challenge1)
            put("challenge2", challenge2)
            put("challenge3", challenge3)
            put("stampsCollected", stampsCollected)
        }

        db.insert("User_Challenge_Info", null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    // User_Challenge_Info 테이블에 새로운 챌린지 정보 업데이트
    fun updateUserChallengeInfo(currentUsername: String, challengeName: String) {
        val db = this.writableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM User_Challenge_Info WHERE username = ? AND 챌린지이름 = ?",
            arrayOf(currentUsername, challengeName)
        )

        if (cursor.moveToFirst()) {
            val contentValues = ContentValues().apply {
                put("챌린지이름", challengeName)
            }
            db.update(
                "User_Challenge_Info",
                contentValues,
                "username = ? AND 챌린지이름 = ? AND 챌린지이름 IS NOT NULL AND 챌린지이름 != ''",
                arrayOf(currentUsername, challengeName)
            )
        } else {
            val contentValues = ContentValues().apply {
                put("username", currentUsername)
                put("챌린지이름", challengeName)
            }

            db.insert("User_Challenge_Info", null, contentValues)

        }
        cursor.close()
        db.close()
    }

    @SuppressLint("Range")
    // 특정 사용자의 총 도장 개수를 조회하는 메서드
    fun getStampsCollected(username: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT stampsCollected FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        var stampsCollected = 0
        if (cursor.moveToFirst()) {
            stampsCollected = cursor.getInt(cursor.getColumnIndex("stampsCollected"))
        }
        cursor.close()
        db.close()
        return stampsCollected
    }

    // 챌린지 삭제 메서드
    fun deleteChallenge(challengeName: String) {
        val db = this.writableDatabase
        db.delete("User_Challenge_Info", "챌린지이름=?", arrayOf(challengeName))
        db.close()
    }

    // 체크된 챌린지 이름을 삭제하는 메서드
    fun deleteChallengesByUsernameAndNames(challengeNames: List<String>) {
        val db = this.writableDatabase
        challengeNames.forEach { challengeName ->
            db.delete(
                "User_Challenge_Info",
                "챌린지이름 = ?",
                arrayOf(challengeName)
            )
        }
        db.close()
    }
}