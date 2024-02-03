package com.example.guru2_android_kida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private val titles = arrayOf("one", "two", "three", "four", "five")
    private val details = arrayOf("Item one", "Item two", "Item three", "Item four", "Itme five")

    private val images = intArrayOf(
        R.drawable.image_exercise_morning_walk,
        R.drawable.image_extra_transcription,
        R.drawable.image_hobby_baking,
        R.drawable.image_life_sleep,
        R.drawable.image_study_english_words
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