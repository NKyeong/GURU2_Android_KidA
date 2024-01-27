package com.example.guru2_android_kida.HomeDetail

data class ToDoItem(
    var isChecked: Boolean = false, // 체크박스 상태
    var todoText: String = "" ,// 할 일 내용
)
