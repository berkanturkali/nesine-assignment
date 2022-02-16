package com.example.nesineassignment.core.util

import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestExecutionThread : PostExecutionThread {
    override val main: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val io: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val default: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
}