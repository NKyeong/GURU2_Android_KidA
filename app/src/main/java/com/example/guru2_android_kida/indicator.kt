package com.example.guru2_android_kida

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class Indicator(private val count: Int, private val context: Context) {

    private val dots = arrayOfNulls<ImageView>(count)

    fun createDotPanel(): View {
        val indicatorLayout = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)
        indicatorLayout.layoutParams = params
        indicatorLayout.orientation = LinearLayout.HORIZONTAL

        for (i in 0 until count) {
            dots[i] = ImageView(context)
            dots[i]?.setImageResource(R.drawable.indicator_dot_unselected)
            indicatorLayout.addView(dots[i])
        }
        selectDot(0) // Initially select the first dot

        return indicatorLayout
    }

    fun selectDot(position: Int) {
        for (i in 0 until count) {
            val drawable =
                if (i == position) R.drawable.indicator_dot_selected else R.drawable.indicator_dot_unselected
            dots[i]?.setImageResource(drawable)
        }
    }
}
