package com.example.nesineassignment.core.di

import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.implementation.PostsCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface PostCacheModule {

    @get:Binds
    val PostsCacheImpl.bindPostsCache: PostsCache
}