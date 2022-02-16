package com.example.nesineassignment.core.data.repo

import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
) : PostsRepo {
    override suspend fun fetchPosts(refresh: Boolean): Flow<Resource<List<Post>>> {
        return flow {}
    }
}