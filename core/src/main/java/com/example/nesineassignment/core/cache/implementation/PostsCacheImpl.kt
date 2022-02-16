package com.example.nesineassignment.core.cache.implementation

import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.dao.PostsDao
import com.example.nesineassignment.core.cache.model.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsCacheImpl @Inject constructor(
    private val dao: PostsDao,
) : PostsCache {

    override suspend fun upsert(post: PostEntity) {
        dao.upsert(post)
    }

    override fun posts(): Flow<List<PostEntity>?> {
        return dao.posts()
    }

    override suspend fun insertAll(posts: List<PostEntity>) {
        dao.insertAll(posts)
    }

    override suspend fun delete(post: PostEntity): Int {
        return dao.delete(post)
    }

    override suspend fun update(post: PostEntity): Int {
        return dao.update(post)
    }

    override suspend fun clear() {
        dao.clear()
    }
}