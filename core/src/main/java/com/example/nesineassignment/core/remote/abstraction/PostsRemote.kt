package com.example.nesineassignment.core.remote.abstraction

import com.example.nesineassignment.core.remote.model.PostDto

interface PostsRemote {
    suspend fun fetchPosts():List<PostDto>
}