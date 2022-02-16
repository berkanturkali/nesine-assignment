package com.example.nesineassignment.home.util

import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.domain.model.Post

internal object DummyData {
    val post = Post(
        body = "    val body: String,\n" +
                "    val id: Int,\n" +
                "    val title: String,\n" +
                "    val userId: Int",
        title = "dolorem dolore est ipsam",
        id = 8,
        userId = 1,
        image = "${BuildConfig.BASE_IMAGE_URL}/id/8/200/"
    )
}