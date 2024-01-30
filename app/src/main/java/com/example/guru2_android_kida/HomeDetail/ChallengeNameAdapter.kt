package com.example.guru2_android_kida.HomeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.R

class ChallengeNameAdapter(private var challengeList: MutableList<String>): RecyclerView.Adapter<ChallengeNameAdapter.ChallengeViewHolder>() {

    class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val challengeName: TextView = itemView.findViewById(R.id.challengeNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_challenge_item, parent, false)
        return ChallengeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.challengeName.text = challengeList[position]
    }

    override fun getItemCount(): Int {
        return challengeList.size
    }

    // 사용자의 챌린지 정보를 업데이트하는 메서드
    fun updateChallengeList(newChallengeList: MutableList<String>) {
        challengeList = newChallengeList
        notifyDataSetChanged()
    }

    // ChallengeViewHolder 내부에 표시할 데이터를 설정하는 메서드 추가
    fun setChallengeList(newChallengeList: List<String>) {
        challengeList.clear()
        challengeList.addAll(newChallengeList)
        notifyDataSetChanged()
    }
}