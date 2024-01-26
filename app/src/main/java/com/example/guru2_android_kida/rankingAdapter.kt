package com.example.challengeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class RankingAdapter(context: Context, data: List<String>) :
    ArrayAdapter<String>(context, R.layout.item_ranking, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_ranking, parent, false)
        }

        val itemText = view?.findViewById<TextView>(R.id.textViewRankingItem)
        itemText?.text = getItem(position)

        return view!!
    }
}