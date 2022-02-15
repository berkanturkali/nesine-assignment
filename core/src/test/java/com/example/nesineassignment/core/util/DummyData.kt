package com.example.nesineassignment.core.util

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
}