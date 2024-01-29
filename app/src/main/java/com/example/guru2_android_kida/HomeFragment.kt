package com.example.guru2_android_kida

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.HomeDetail.ToDoAdapter
import com.example.guru2_android_kida.HomeDetail.ToDoItem
import com.example.guru2_android_kida.HomeDetail.TodoDBHelper
import java.util.Locale

// 홈 액티비티 입니다.
// 캘린더와 챌린지 리스트가 뜨는 파일이며, 위의 HomeActivity는 각각의 플래그먼트를 띄우기 위한 메인화면으로 보면 됨.
class HomeFragment : Fragment(R.layout.fragment_home) {
    // 데이터베이스 헬퍼 인스턴스
    private lateinit var dbHelper: TodoDBHelper
    // UI 요소들
    private lateinit var calendarView: CalendarView
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var toDoAdapter: ToDoAdapter

    // 초기에는 빈 리스트로 초기화
    private val todoList = mutableListOf<ToDoItem>()

    // todoListCache 추가
    private val todoListCache = mutableMapOf<String, List<ToDoItem>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 프래그먼트의 레이아웃을 inflate
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 데이터베이스 헬퍼 초기화
        dbHelper = TodoDBHelper(requireContext())

        //캘린더 뷰
        calendarView = view.findViewById(R.id.calendarView)
        // 리사이클러뷰
        recyclerView = view.findViewById(R.id.recyclerView)
        //할 일 추가 버튼
        addButton = view.findViewById(R.id.addButton)

        // Adapter를 초기화할 때 빈 리스트로 설정

        //todolist어댑터 설정
        toDoAdapter = ToDoAdapter(todoList)
        // 리사이클러뷰 어댑터 설정
        recyclerView.adapter = toDoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CalendarView의 날짜 변경 이벤트 처리
        calendarView.setOnDateChangeListener { View, year, month, dayOfMonth ->
            val selectedDate = getFormattedDate(year, month, dayOfMonth)
            showTodoList(selectedDate)

            // todoListCache에서 해당 날짜의 할 일 목록 가져오기
            val cachedTodoList = todoListCache[selectedDate]

            if (cachedTodoList != null) {
                // 캐시된 데이터가 있으면 그대로 화면 갱신
                updateUI(cachedTodoList)
            } else {
                // 캐시된 데이터가 없으면 데이터베이스에서 가져와서 화면 갱신
                showTodoList(selectedDate)
            }
        }

        // 추가 버튼을 클릭 시
        addButton.setOnClickListener {
            addTodoItem()
        }

        // 초기 화면에는 오늘 날짜의 할 일 목록을 보여줌
        val currentDate = getCurrentFormattedDate()
        showTodoList(currentDate)

        return view
    }

    // 할 일 아이템을 리스트에 추가하는 메서드
    private fun addTodoItem() {
        // 새로운 할 일 아이템 생성
        val newTodoItem = ToDoItem()
        // Adapter에 아이템 추가
        toDoAdapter.addItem(newTodoItem)

        // 데이터베이스에도 추가
        dbHelper.insertTodoItem(getCurrentFormattedDate(), newTodoItem)

        // 해당 날짜의 할 일 목록 캐시 갱신
        val currentDate = getCurrentFormattedDate()
        val updatedTodoList = dbHelper.getTodosForDate(currentDate)
        todoListCache[currentDate] = updatedTodoList

    }

    // 날짜를 "yyyy-MM-dd" 형식으로 포맷하는 유틸리티 함수
    private fun getCurrentFormattedDate(): String {
        // 날짜를 "yyyy-MM-dd" 형식으로 포맷
        val currentDate = Calendar.getInstance()
        return getFormattedDate(
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
    }

    // 선택한 날짜에 해당하는 할 일 목록을 가져와서 RecyclerView에 표시
    private fun showTodoList(selectedDate: String) {

        // 데이터베이스에서 해당 날짜의 할 일 목록을 가져오기
        val todoListFromDB = dbHelper.getTodosForDate(selectedDate)

        // todoListCache에 캐시 데이터 저장
        todoListCache[selectedDate] = todoListFromDB

        // 어댑터의 내부 리스트를 업데이트하고 RecyclerView 갱신
        toDoAdapter.setToDoList(todoListFromDB)


    }
    // 기존에 사용하던 updateUI 함수
    private fun updateUI(newList: List<ToDoItem>) {
        toDoAdapter.updateList(newList)
    }

    // 날짜를 "yyyy-MM-dd" 형식으로 포맷하는 유틸리티 함수
    private fun getFormattedDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}