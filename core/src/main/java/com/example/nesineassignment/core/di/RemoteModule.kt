package com.example.nesineassignment.core.di

import com.example.nesineassignment.core.BuildConfig
import com.example.nesineassignment.core.remote.abstraction.PostsRemote
import com.example.nesineassignment.core.remote.impl.PostsRemoteImpl
import com.example.nesineassignment.core.remote.service.ApiService
import com.example.nesineassignment.remote.factory.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RemoteModule {

    @get:Binds
    val PostsRemoteImpl.bindPostsRemote: PostsRemote

    companion object {
        @[Provides Singleton]
        fun apiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(BuildConfig.BASE_URL, BuildConfig.DEBUG)
                .create(ApiService::class.java)
    }
}