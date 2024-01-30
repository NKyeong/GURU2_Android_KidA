package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.Challenge.ChallengeList

// ChallengeAdapter 클래스: RecyclerView의 데이터를 관리하고 화면에 표시하는 어댑터
class ChallengeAdapter : RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    // 챌린지 목록을 저장하는 변수
    private var challengeList: List<ChallengeList> = ArrayList()

    // onCreateViewHolder 함수: ViewHolder 객체를 생성하여 반환하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        // 레이아웃 파일을 인플레이트하여 ViewHolder 객체 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_challenge_explaination, parent, false)
        return ChallengeViewHolder(view)
    }

    // onBindViewHolder 함수: ViewHolder에 데이터를 바인딩하는 함수
    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        // 위치(position)에 해당하는 챌린지 객체를 가져와 ViewHolder에 바인딩
        val challenge = challengeList[position]
        holder.bind(challenge)
    }

    // getItemCount 함수: 목록의 크기를 반환하는 함수
    override fun getItemCount(): Int {
        return challengeList.size
    }

    // submitList 함수: 새로운 챌린지 목록을 받아와서 업데이트하는 함수
    fun submitList(list: List<ChallengeList>) {
        challengeList = list
        notifyDataSetChanged() // 데이터 변경을 알려서 화면을 갱신
    }

    // ChallengeViewHolder 클래스: RecyclerView의 각 아이템을 위한 ViewHolder 클래스
    inner class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // bind 함수: 챌린지 객체를 ViewHolder에 바인딩하는 함수
        fun bind(challenge: ChallengeList) {
            // ViewHolder에 있는 TextView에 챌린지 제목과 설명을 설정
            itemView.findViewById<TextView>(R.id.text_challenge_title).text = challenge.challengeName
            itemView.findViewById<TextView>(R.id.text_challenge_description).text = challenge.challengeDescription
        }
    }
}
