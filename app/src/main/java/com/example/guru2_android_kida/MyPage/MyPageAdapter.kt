package com.example.guru2_android_kida.HomeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeDBHelper
import com.example.guru2_android_kida.R

// ChallengeNameAdapter 클래스: RecyclerView의 데이터를 관리하고 화면에 표시하는 어댑터
class MyPageAdapter(private var challengeNameLIst: MutableList<String>, // 챌린지 목록을 저장하는 변수
                           private val ChallengeNamedbHelper: ChallengeDBHelper, // ChallengeDBHelper 인스턴스를 전달받는 변수
                           private val listener: OnChallengeClickListener // 인터페이스 추가
): RecyclerView.Adapter<MyPageAdapter.ChallengeViewHolder>() {

    interface OnChallengeClickListener {
        fun onChallengeClick(challengeName: String)
    }

    // ChallengeViewHolder 클래스: RecyclerView의 각 아이템을 위한 ViewHolder 클래스
    class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val challengeName: TextView = itemView.findViewById(R.id.challengeEditTextView)
    }

    // onCreateViewHolder 함수: ViewHolder 객체를 생성하여 반환하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        // 레이아웃 파일을 인플레이트하여 ViewHolder 객체 생성
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_page_challenge_item, parent, false)
        return ChallengeViewHolder(itemView)
    }

    // onBindViewHolder 함수: ViewHolder에 데이터를 바인딩하는 함수
    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.challengeName.text = challengeNameLIst[position]

        holder.itemView.findViewById<TextView>(R.id.challengeEditTextView).setOnClickListener {
            val clickedChallengeName = challengeNameLIst[position]
            listener.onChallengeClick(clickedChallengeName)
        }
    }

    // getItemCount 함수: 목록의 크기를 반환하는 함수
    override fun getItemCount(): Int {
        return challengeNameLIst.size
    }

    // 사용자의 챌린지 정보를 업데이트하는 메서드
    fun setChallengeListForUser(username: String) {
        // ChallengeDBHelper를 사용하여 사용자의 챌린지 목록을 가져옴
        val userChallenges = ChallengeNamedbHelper.getChallengesForUser(username)
        // 어댑터의 내부 리스트를 업데이트하고 RecyclerView 갱신
        setChallengeList(userChallenges)
    }

    // ChallengeViewHolder 내부에 표시할 데이터를 설정하는 메서드 추가
    fun setChallengeList(newChallengeList: List<String>) {
        // 내부 리스트를 새로운 목록으로 업데이트
        challengeNameLIst.clear()
        challengeNameLIst.addAll(newChallengeList)
        // RecyclerView 갱신
        notifyDataSetChanged()
    }
}