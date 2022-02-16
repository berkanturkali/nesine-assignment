package com.example.nesineassignment.home.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.nesineassignment.common.R

fun ImageView.load(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable,
) {
    url?.let {
        Glide.with(this).load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_launcher_background).into(this)
    }
}