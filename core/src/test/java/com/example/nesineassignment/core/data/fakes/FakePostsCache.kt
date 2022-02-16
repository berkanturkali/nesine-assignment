package com.example.nesineassignment.core.data.fakes

import com.example.nesineassignment.DummyData.postEntity
import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.model.PostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakePostsCache : PostsCache {

    private val cache = LinkedHashMap<Int, PostEntity>()

    override suspend fun upsert(post: PostEntity) {
        cache[postEntity.id] = postEntity
    }

    override fun posts(): Flow<List<PostEntity>?> {
        return if (cache.size != 0) {
            flow { emit(cache.values.toList()) }
        } else {
            flow { emit(listOf()) }

        }
    }

    override suspend fun insertAll(posts: List<PostEntity>) {
        posts.forEach {
            cache[it.id] = it
        }
    }

    override suspend fun delete(post: PostEntity): Int {
        cache.remove(post.id)
        return post.id
    }

    override suspend fun update(post: PostEntity): Int {
        cache[post.id] = post
        return post.id
    }

    override suspend fun clear() {
        cache.clear()
    }
}