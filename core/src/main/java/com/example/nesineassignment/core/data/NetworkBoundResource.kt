package com.example.nesineassignment.core.data

import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

inline fun <ResultType, RequestType, DomainType> networkBoundResource(
    crossinline query: () -> Flow<ResultType?>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    refresh: Boolean = true,
    crossinline shouldFetch: (ResultType) -> Boolean,
    crossinline mapFromEntity: (ResultType) -> DomainType,
    postExecutionThread: PostExecutionThread,
) = channelFlow<Resource<DomainType>> {
    val data = query().first()
    if (refresh) {
        if (shouldFetch(data!!)) {
            send(Resource.Loading())
            try {
                val response = withContext(postExecutionThread.io) { fetch() }
                saveFetchResult(response)
                query().map {
                    mapFromEntity(it!!)
                }
                    .collect {
                        send(Resource.Success(it))
                    }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().collect {
                    send(Resource.Error(throwable))
                }
            }
        } else {
            query().map {
                mapFromEntity(it!!)
            }
                .collect {
                    send(Resource.Success(it))
                }
        }
    } else {
        query().map {
            mapFromEntity(it!!)
        }
            .collect {
                send(Resource.Success(it))
            }
    }
}