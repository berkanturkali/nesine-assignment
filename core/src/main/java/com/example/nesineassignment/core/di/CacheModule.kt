package com.example.nesineassignment.core.di

import android.content.Context
import com.example.nesineassignment.core.cache.dao.PostsDao
import com.example.nesineassignment.core.cache.db.PostsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): PostsDb {
        return PostsDb.build(context)
    }

    @[Provides Singleton]
    fun providePostsDao(db: PostsDb): PostsDao = db.postsDao
}