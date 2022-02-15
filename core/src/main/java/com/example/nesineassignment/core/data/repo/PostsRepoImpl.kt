package com.example.nesineassignment.core.data.repo

import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import kotlinx.coroutines.flow.Flow

class PostsRepoImpl:PostsRepo {
    override suspend fun fetchPosts(refresh: Boolean): Flow<Resource<List<Post>>> {

    }
}