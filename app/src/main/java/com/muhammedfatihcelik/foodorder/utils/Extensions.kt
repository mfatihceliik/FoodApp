package com.muhammedfatihcelik.foodorder.utils

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.muhammedfatihcelik.foodorder.R

/* PROGRESSBAR */
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.gone() {
    visibility = View.GONE
}

fun ProgressBar.hide() {
    visibility = View.INVISIBLE
}

/* AlertDialog */
fun AlertDialog.Builder.set(title: String, message: String, imageId: Int? = R.drawable.ic_home) {
    setTitle(title)
    setMessage(message)
    if (imageId != null) {
        setIcon(imageId)
    }
    setPositiveButton("ok") { dialogInterface, i ->
        dialogInterface.dismiss()
    }
    show()
}

fun TextView.setRestaurantPoint(restaurantPoint: Double): String {
    val point = StringBuilder()
    point.append("(")
    point.append(restaurantPoint)
    point.append(")")
    when {
        restaurantPoint <= 5.0 -> {
            setTextColor(Color.RED)
        }
        restaurantPoint in 5.1..6.9 -> {
            setTextColor(Color.YELLOW)
        }
        restaurantPoint in 7.0..10.0 -> {
            setTextColor(Color.GREEN)
        }
    }
    return point.toString()
}
