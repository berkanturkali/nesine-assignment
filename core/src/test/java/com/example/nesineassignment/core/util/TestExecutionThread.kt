package com.example.nesineassignment.core.util

import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestExecutionThread : PostExecutionThread {
    override val main: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val io: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val default: CoroutineDispatcher
        get() = StandardTestDispatcher()
}