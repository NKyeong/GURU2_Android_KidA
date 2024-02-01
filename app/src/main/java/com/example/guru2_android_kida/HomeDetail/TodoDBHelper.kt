package com.example.guru2_android_kida.HomeDetail

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TodoDBHelper(context: Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    // 데이터베이스 및 테이블 정보 상수
    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "todos"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TODO = "todo"
        private const val COLUMN_CHECKED = "checked"
    }

    // 데이터베이스 테이블 생성 쿼리
    private val createTable =
        "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_DATE TEXT, $COLUMN_TODO TEXT, $COLUMN_CHECKED INTEGER)"


    // 데이터베이스 초기 생성 시 호출되는 메서드
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTable)
    }

    // 데이터베이스 업그레이드 시 호출되는 메서드
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // 할 일 추가 메서드
    fun insertTodoItem(date: String, todo: ToDoItem) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_TODO, todo.todoText)
        values.put(COLUMN_CHECKED, if (todo.isChecked) 1 else 0) // 체크 여부 저장
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    // 선택한 날짜의 할 일 목록을 가져오는 메서드
    fun getTodosForDate(date: String): List<ToDoItem> {
        val todoList = mutableListOf<ToDoItem>()
        val db = this.readableDatabase
        val query = "SELECT $COLUMN_TODO, $COLUMN_CHECKED FROM $TABLE_NAME WHERE $COLUMN_DATE = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(date))
        if (cursor.moveToFirst()) {
            do {
                val todoText = cursor.getString(cursor.getColumnIndex(COLUMN_TODO))
                val isChecked = cursor.getInt(cursor.getColumnIndex(COLUMN_CHECKED)) == 1
                val todoItem = ToDoItem(isChecked, todoText)
                todoList.add(todoItem)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return todoList
    }
    // 할 일 업데이트 메서드
    fun updateTodoItem(date: String, todo: ToDoItem) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_TODO, todo.todoText)
        values.put(COLUMN_CHECKED, if (todo.isChecked) 1 else 0) // 체크 여부 저장
        val whereClause = "$COLUMN_DATE = ? AND $COLUMN_TODO = ?"
        val whereArgs = arrayOf(date, todo.todoText)
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }
}