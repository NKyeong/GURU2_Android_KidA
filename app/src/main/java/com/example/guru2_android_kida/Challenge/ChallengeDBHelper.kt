package com.example.guru2_android_kida.Challenge

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChallengeDBHelper(context: Context) :
    SQLiteOpenHelper(context, "Challenge_List_DB.db", null, 1) {

    override fun onCreate(challengedb: SQLiteDatabase?) {
        challengedb!!.execSQL("CREATE TABLE Challenge_List_DB(카테고리 text, 챌린지이름 text)")
    }

}