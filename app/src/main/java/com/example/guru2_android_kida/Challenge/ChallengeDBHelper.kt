package com.example.guru2_android_kida.Challenge

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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

    // 데이터베이스 업그레이드: 테이블을 삭제하고 다시 생성
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User_Challenge_Info")
        onCreate(db)
    }

    // 사용자가 챌린지에 참여하는 메서드
    /*fun joinChallenge(username: String, challengeName: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("username", username)
            put("챌린지이름", challengeName)
        }

        val result = db.insert("User_Challenge_Info", null, contentValues)
        db.close()

        return result != -1L
    }*/

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

    // User_Challenge_Info 테이블에 해당 username이 이미 존재하는지 확인하는 메서드
    /*fun isUsernameExists(username: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }*/

    // User_Challenge_Info 테이블에 username을 추가하는 메서드
    /*fun addUsernameToUserChallengeInfo(username: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("username", username)
        }
        val result = db.insert("User_Challenge_Info", null, contentValues)
        db.close()
        return result != -1L
    }*/
    // 도전과제 데이터 저장 및 도장 개수 업데이트
    fun saveChallengeData(username: String, challengeName: String, challenge1: String, challenge2: String, challenge3: String, stampsCollected: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("challenge1", challenge1)
            put("challenge2", challenge2)
            put("challenge3", challenge3)
            put("stampsCollected", stampsCollected)
        }

        // 기존 행 업데이트
        db.update("User_Challenge_Info", contentValues, "username = ? AND 챌린지이름 = ?", arrayOf(username, challengeName))
        db.close()
    }



    @SuppressLint("Range")
    // User_Challenge_Info 테이블에 새로운 챌린지 정보 추가 또는 업데이트
    fun updateUserChallengeInfo(currentUsername: String, challengeName: String) {
        val db = this.writableDatabase
        // 해당 사용자와 챌린지에 대한 정보를 가져옴
        val cursor = db.rawQuery(
            "SELECT * FROM User_Challenge_Info WHERE username = ? AND 챌린지이름 = ?",
            arrayOf(currentUsername, challengeName)
        )
        if (cursor.moveToFirst()) {
            // 이미 해당 사용자와 챌린지에 대한 정보가 있으면 업데이트
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

            // 해당 사용자와 챌린지에 대한 정보가 없으면 추가
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

    @SuppressLint("Range")
    // 사용자의 username 불러오기
    fun getUsername(username: String): String? {
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery("SELECT username FROM User_Challenge_Info WHERE username = ?", arrayOf(username))
        } catch (e: Exception) {
            db.execSQL("CREATE TABLE IF NOT EXISTS User_Challenge_Info(username TEXT, 챌린지이름 TEXT)")
            return null
        }

        var result: String? = null

        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex("username"))
        }

        cursor.close()
        db.close()

        return result
    }


    // 챌린지 삭제 메서드
    fun removeChallenges(challengeIds: List<Int>): Boolean {
        val db = this.writableDatabase

        try {
            for (id in challengeIds) {
                db.delete("User_Challenge_Info", "_id = ?", arrayOf(id.toString()))
            }
            return true
        } catch (e: Exception) {
            // 오류 처리
            return false
        } finally {
            db.close()
        }
    }
    data class ChallengeInfo(
        val challenge1: String,
        val challenge2: String,
        val challenge3: String,
        val stampsCollected: Int
    )
    fun getChallengeInfo(username: String, challengeName: String): ChallengeInfo? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT challenge1, challenge2, challenge3, stampsCollected FROM User_Challenge_Info WHERE username = ? AND 챌린지이름 = ?",
            arrayOf(username, challengeName)
        )
        var challengeInfo: ChallengeInfo? = null
        if (cursor.moveToFirst()) {
            val challenge1 = cursor.getString(cursor.getColumnIndex("challenge1"))
            val challenge2 = cursor.getString(cursor.getColumnIndex("challenge2"))
            val challenge3 = cursor.getString(cursor.getColumnIndex("challenge3"))
            val stampsCollected = cursor.getInt(cursor.getColumnIndex("stampsCollected"))
            challengeInfo = ChallengeInfo(challenge1, challenge2, challenge3, stampsCollected)
        }
        cursor.close()
        db.close()
        return challengeInfo
    }

}