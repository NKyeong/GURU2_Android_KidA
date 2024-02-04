package com.example.guru2_android_kida.Challenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.R

interface ChallengeItemClickListener {
    fun onChallengeStartClicked(
        challengeName: String,
        position: Int,
        challengeList: List<ChallengeList>
    )
}
class ChallengeAdapter(private val clickListener: ChallengeItemClickListener) : RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    private var challengeList: List<ChallengeList> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_challenge_explaination, parent, false)
        return ChallengeViewHolder(view)
    }
    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = challengeList[position]
        holder.bind(challenge)

        // 챌린지 시작하기 버튼
        holder.itemView.findViewById<Button>(R.id.btn_start_challenge).setOnClickListener {
            clickListener.onChallengeStartClicked(challenge.challengeName, position, challengeList)
            DataManager.currentChallengeName = challenge.challengeName

            val context = holder.itemView.context
            val intent = Intent(context, PersonalChallengeActivity::class.java)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return challengeList.size
    }

    fun submitList(list: List<ChallengeList>) {
        challengeList = list
        notifyDataSetChanged()
    }

    inner class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(challenge: ChallengeList) {
            itemView.findViewById<TextView>(R.id.text_challenge_title).text = challenge.challengeName
            itemView.findViewById<TextView>(R.id.text_challenge_description).text = challenge.challengeDescription

            val imageName = challenge.imageName
            val resourceId = itemView.context.resources.getIdentifier(imageName, "drawable", itemView.context.packageName)
            if (resourceId != 0) {
                itemView.findViewById<ImageView>(R.id.image_challenge).setImageResource(resourceId)
            }
        }
    }

}