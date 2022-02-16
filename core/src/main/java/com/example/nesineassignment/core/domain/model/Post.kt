package com.example.nesineassignment.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val image: String,
):Parcelable