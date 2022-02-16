package com.example.nesineassignment.core.cache.abstraction

import com.example.nesineassignment.core.cache.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostsCache {
    suspend fun upsert(post: PostEntity)
    fun posts(): Flow<List<PostEntity>>
    suspend fun insertAll(posts: List<PostEntity>)
    suspend fun delete(post: PostEntity): Int
    suspend fun update(post: PostEntity): Int
    suspend fun clear()
}