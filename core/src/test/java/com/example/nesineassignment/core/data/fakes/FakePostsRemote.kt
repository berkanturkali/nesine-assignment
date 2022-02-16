package com.example.nesineassignment.core.data.fakes

import com.example.nesineassignment.DummyData
import com.example.nesineassignment.core.remote.abstraction.PostsRemote
import com.example.nesineassignment.core.remote.model.PostDto
import java.io.IOException

internal class FakePostsRemote:PostsRemote {
    override suspend fun fetchPosts(): List<PostDto> {
        return listOf(DummyData.postDto)
    }
}
internal class FakeErrorPostsRemote:PostsRemote{
    override suspend fun fetchPosts(): List<PostDto> {
        throw IOException()
    }
}