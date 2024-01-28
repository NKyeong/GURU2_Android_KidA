package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRankingAdapter(private val rankingList: List<UserRanking>) : RecyclerView.Adapter<UserRankingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_ranking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userRanking = rankingList[position]
        holder.userName.text = userRanking.userName
        holder.stampsCount.text = "${userRanking.stampsCount} stamps"
    }

    override fun getItemCount() = rankingList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.tvUserName)
        val stampsCount: TextView = view.findViewById(R.id.tvStampsCount)
    }
}
