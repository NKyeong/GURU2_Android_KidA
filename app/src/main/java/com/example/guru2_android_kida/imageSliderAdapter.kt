package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private val titles = arrayOf("미라클모닝", "집밥 챌린지", "만보걷기", "피포페인팅", "영어뉴스 듣기")
    private val details = arrayOf(
        "일찍 일어나는 새가 벌레를 먹는다! 부지런한 습관으로 당신의 일상을 새롭게 다듬어 보세요. 상쾌한 아침에는 어떤 일을 할 수 있을까요? 아침마다 하고 싶은 목표를 설정하고 꾸준하게 이루어가며 KidA와 함께 도장판을 채워가요!",
        "물가가 오르며 한끼에 드는 돈이 훌쩍 올라가 곤란하던 경험이 있지 않으신가요? 그래서 요즘은 간단한 식재료로 직접 음식을 해먹는 집밥 챌린지가 유행이랍니다. 목표를 설정하고 꾸준하게 이루어가며 KidA와 함께 도장판을 채워가요!",
        "하루 평균 만보도 걷는게 힘든 당신! 건강을 위해 약간의 시간과 노력을 기울여 하루에 만보를 채워보세요. 목표를 설정하고 꾸준하게 이루어가며 KidA와 함께 도장판을 채워가요!",
        "최근 명화 그리기 피포페인팅이 유행이였어요. 꾸준함과 열정만 있다면 내 손안에서도 멋진 명화가 탄생! 시간을 정해 목표를 설정하고 꾸준하게 이루어가며 KidA와 함께 도장판을 채워가요!",
        "영어 문장을 읽고 해석하는 것은 자신이 있지만 말하는 것에 자신이 없는 당신! 영어 뉴스를 들으며 자연스럽게 회화에 익숙해져보세요. 목표를 설정하고 꾸준하게 이루어가며 KidA와 함께 도장판을 채워가요!")

    private val images = intArrayOf(
        R.drawable.image_life_miracle_morning,
        R.drawable.image_food_home_meal,
        R.drawable.image_exercise_walk,
        R.drawable.image_hobby_painting,
        R.drawable.image_study_english_news
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_challenge_explaination, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemImage.setImageResource(images[position])
        holder.itemDetail.text = details[position]
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.image_challenge)
        var itemTitle: TextView = itemView.findViewById(R.id.text_challenge_title)
        var itemDetail: TextView = itemView.findViewById(R.id.text_challenge_description)
    }
}