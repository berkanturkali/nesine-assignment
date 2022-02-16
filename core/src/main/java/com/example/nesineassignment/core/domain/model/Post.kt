package com.example.nesineassignment.core.domain.model

import android.os.Parcelable
import android.text.TextUtils.replace
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val image: String,
):Parcelable{

    fun replacedTitle():String{
        return this.title.replace("\n"," ")
    }

    fun replacedBody():String{
        return this.body.replace("\n"," ")
    }
}