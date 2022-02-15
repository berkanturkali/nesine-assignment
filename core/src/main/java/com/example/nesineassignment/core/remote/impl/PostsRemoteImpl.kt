package com.example.nesineassignment.core.remote.impl

import com.example.nesineassignment.core.remote.abstraction.PostsRemote
import com.example.nesineassignment.core.remote.model.PostDto
import com.example.nesineassignment.core.remote.service.ApiService
import javax.inject.Inject

class PostsRemoteImpl @Inject constructor(
    private val api: ApiService
): PostsRemote {
    override suspend fun fetchPosts(): List<PostDto> =api.fetchPosts()
}