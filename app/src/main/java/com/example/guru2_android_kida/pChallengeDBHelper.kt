package com.example.guru2_android_kida

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class pChallengeDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Challenge.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE challenges (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "challenge1 TEXT," +
                    "challenge2 TEXT," +
                    "challenge3 TEXT," +
                    "stampsCollected INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS challenges"
    }
    // 데이터베이스 생성: 'challenges' 테이블을 생성
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    // 데이터베이스 업그레이드: 테이블을 삭제하고 다시 생성
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}