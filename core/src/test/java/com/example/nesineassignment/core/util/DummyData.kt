package com.example.nesineassignment.core.util

import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.cache.model.PostEntity
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.remote.model.PostDto

internal object DummyData {

    val postDto = PostDto(
        body = "    val body: String,\n" +
                "    val id: Int,\n" +
                "    val title: String,\n" +
                "    val userId: Int",
        title = "dolorem dolore est ipsam",
        id = 8,
        userId = 1
    )

    val postEntity = PostEntity(
        body = "    val body: String,\n" +
                "    val id: Int,\n" +
                "    val title: String,\n" +
                "    val userId: Int",
        title = "dolorem dolore est ipsam",
        id = 8,
        userId = 1
    )

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