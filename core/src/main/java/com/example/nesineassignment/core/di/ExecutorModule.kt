package com.example.nesineassignment.core.di

import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import com.example.nesineassignment.core.domain.executor.implementation.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}