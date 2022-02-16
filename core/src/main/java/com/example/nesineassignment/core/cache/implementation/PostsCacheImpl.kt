package com.example.nesineassignment.core.cache.implementation

import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.dao.PostsDao
import com.example.nesineassignment.core.cache.model.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsCacheImpl @Inject constructor(
    private val dao: PostsDao
) : PostsCache {

    override suspend fun upsert(post: PostEntity) {

    }

    override fun posts(): Flow<List<PostEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(posts: List<PostEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(post: PostEntity): Int {
        TODO("Not yet implemented")
    }

    override suspend fun update(post: PostEntity): Int {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}