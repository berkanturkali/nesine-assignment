package com.example.nesineassignment.core.di

import com.example.nesineassignment.core.data.repo.PostsRepoImpl
import com.example.nesineassignment.core.domain.repo.PostsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepoModule {

    @get:[Binds]
    val PostsRepoImpl.postsRepo: PostsRepo
}