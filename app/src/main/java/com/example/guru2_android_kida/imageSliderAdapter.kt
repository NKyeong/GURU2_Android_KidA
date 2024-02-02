package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private val images = intArrayOf(
        R.drawable.image_exercise_morning_walk,
        R.drawable.image_extra_transcription,
        R.drawable.image_hobby_baking,
        R.drawable.image_life_sleep,
        R.drawable.image_study_english_words
    )

    class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val popularImageView: ImageView = itemView.findViewById(R.id.popularViewPager)
        val popularIndicatorDot: ImageView = itemView.findViewById(R.id.popularIndicatorDot)

        val myChallengeImageView: ImageView = itemView.findViewById(R.id.myChallengeViewPager)
        val myChallengeIndicatorDot: ImageView = itemView.findViewById(R.id.myChallengeIndicatorDot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_challenge_explaination, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.popularImageView.setImageResource(images[position])
        holder.myChallengeImageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

}