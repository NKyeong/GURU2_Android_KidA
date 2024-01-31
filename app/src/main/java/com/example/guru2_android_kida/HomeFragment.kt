package com.example.guru2_android_kida

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.Challenge.ChallengeDetailActivity
import com.example.guru2_android_kida.HomeDetail.ChallengeNameAdapter
import com.example.guru2_android_kida.HomeDetail.ToDoAdapter
import com.example.guru2_android_kida.HomeDetail.ToDoItem
import com.example.guru2_android_kida.HomeDetail.TodoDBHelper
import java.util.Locale

// 홈 액티비티 입니다.
// 캘린더와 챌린지 리스트가 뜨는 파일이며, 위의 HomeActivity는 각각의 플래그먼트를 띄우기 위한 메인화면으로 보면 됨.
class HomeFragment : Fragment(R.layout.fragment_home), ChallengeNameAdapter.OnChallengeClickListener {
    // 데이터베이스 헬퍼 인스턴스
    private lateinit var dbHelper: TodoDBHelper
    private lateinit var challengeDBHelper: ChallengeDBHelper // ChallengeDBHelper 추가

    // UI 요소들
    private lateinit var calendarView: CalendarView
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var challengeNameAdapter: ChallengeNameAdapter // ChallengeNameAdapter 추가



    // 초기에는 빈 리스트로 초기화
    private val todoList = mutableListOf<ToDoItem>()
    private val challengeNameLIst = mutableListOf<String>() // 챌린지 정보 리스트 추가

    // todoListCache 추가, challengeListCache 추가
    private val todoListCache = mutableMapOf<String, List<ToDoItem>>()
    private val challengeListCache = mutableMapOf<String, List<String>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 프래그먼트의 레이아웃을 inflate
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 데이터베이스 헬퍼 초기화
        dbHelper = TodoDBHelper(requireContext())
        challengeDBHelper = ChallengeDBHelper(requireContext()) // ChallengeDBHelper 초기화 추가

        //캘린더 뷰
        calendarView = view.findViewById(R.id.calendarView)
        // 리사이클러뷰
        recyclerView = view.findViewById(R.id.recyclerView)
        //할 일 추가 버튼
        addButton = view.findViewById(R.id.addButton)

        // Adapter를 초기화할 때 빈 리스트로 설정

        //todolist어댑터 설정
        toDoAdapter = ToDoAdapter(todoList)
        challengeNameAdapter = ChallengeNameAdapter(challengeNameLIst, challengeDBHelper, this) // ChallengeNameAdapter 초기화 추가

        // 리사이클러뷰 어댑터 설정
        recyclerView.adapter = toDoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ChallengeNameAdapter를 적용하도록 추가
        val challengeRecyclerView = view.findViewById<RecyclerView>(R.id.challengeRecyclerView)
        challengeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        challengeRecyclerView.adapter = challengeNameAdapter

        // 홈 화면이 로딩될 때 바로 챌린지 목록을 표시
        showChallengeList()

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

    // 홈 화면이 로딩될 때 챌린지 목록을 가져와 표시하는 메서드 추가
    private fun showChallengeList() {
        // 현재 로그인한 사용자의 이름을 가져오는 메서드 (적절한 메서드로 대체)

        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val currentUsername = sharedPreferences.getString("current_username", "") ?: ""

        // ChallengeNameAdapter를 사용하여 사용자의 챌린지 목록을 가져와 화면에 표시
        challengeNameAdapter.setChallengeListForUser(currentUsername)

        // 텍스트 뷰에 챌린지이름을 표시
        val challengeNameTextView = view?.findViewById<TextView>(R.id.challengeNameTextView)
        if (challengeNameLIst.isEmpty()) {
            challengeNameTextView?.text = "참여 중인 챌린지가 없습니다."
        } else {
            challengeNameTextView?.text = challengeNameLIst.joinToString("\n")
        }
    }

    // 현재 로그인한 사용자의 챌린지 목록을 가져오는 가상의 메서드
    /*private fun getCurrentChallengename(): String {

        return currentUsername
    }*/

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

    // ChallengeNameAdapter.OnChallengeClickListener 구현, 챌린지 텍스트를 눌렀을 때 화면 전환
    override fun onChallengeClick(challengeName: String) {
        // 챌린지 이름을 클릭했을 때의 처리
        // ChallengeDetailActivity로 전환하고, 필요한 데이터를 전달
        val intent = Intent(requireContext(), ChallengeDetailActivity::class.java)
        intent.putExtra("challengeName", challengeName)
        startActivity(intent)

    }
}