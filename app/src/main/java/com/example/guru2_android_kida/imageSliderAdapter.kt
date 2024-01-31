package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private val images = intArrayOf(
        R.drawable.exercise_stairs,
        R.drawable.study_coding,
        R.drawable.life_diary,
        R.drawable.food_fasting,
        R.drawable.hobby_croquis
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_slider, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.popularImageView.setImageResource(images[position])
        holder.myChallengeImageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val popularImageView: ImageView = itemView.findViewById(R.id.popularViewPager)
        val popularIndicatorDot: ImageView = itemView.findViewById(R.id.popularIndicatorDot)

        val myChallengeImageView: ImageView = itemView.findViewById(R.id.myChallengeViewPager)
        val myChallengeIndicatorDot: ImageView = itemView.findViewById(R.id.myChallengeIndicatorDot)
    }
}