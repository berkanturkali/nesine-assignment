package com.example.nesineassignment.core.domain.repo

import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepo {
    suspend fun fetchPosts(refresh: Boolean): Flow<Resource<List<Post>>>

    suspend fun remove(post:Post)
}