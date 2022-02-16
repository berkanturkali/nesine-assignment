package com.example.nesineassignment.home.fakes

import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import com.example.nesineassignment.home.util.DummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException

class FakePostsRepo : PostsRepo {

    private var postFlow: Flow<Resource<List<Post>>> =
        flowOf(Resource.Success(listOf(DummyData.post)))

    var responseType: Resource<List<Post>> = Resource.Success()
        set(value) {
            field = value
            postFlow = makeResponse(value)
        }

    private fun makeResponse(type: Resource<*>): Flow<Resource<List<Post>>> {
        return when (type) {
            is Resource.Error -> flowOf(Resource.Error(IOException()))
            is Resource.Success -> flowOf(Resource.Success(listOf(DummyData.post)))
            is Resource.Loading -> flowOf()
        }
    }

    override suspend fun fetchPosts(refresh: Boolean): Flow<Resource<List<Post>>> {
        return postFlow
    }

    override suspend fun remove(post: Post) {}

    override suspend fun update(post: Post): Int {
        return -1
    }

}