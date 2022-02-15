package com.example.nesineassignment.core.remote.service

import com.example.nesineassignment.core.remote.model.PostDto
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun fetchPosts(): List<PostDto>
}