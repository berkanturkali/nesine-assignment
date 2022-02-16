package com.example.nesineassignment.core.data.repo

import android.text.TextUtils.isEmpty
import androidx.room.withTransaction
import com.example.nesineassignment.core.cache.abstraction.PostsCache
import com.example.nesineassignment.core.cache.db.PostsDb
import com.example.nesineassignment.core.cache.mapper.PostCacheMapper
import com.example.nesineassignment.core.data.networkBoundResource
import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import com.example.nesineassignment.core.remote.abstraction.PostsRemote
import com.example.nesineassignment.core.remote.mapper.PostMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val postsRemote: PostsRemote,
    private val postsCache:PostsCache,
    private val postExecutionThread: PostExecutionThread,
    private val cacheMapper: PostCacheMapper,
    private val remoteMapper: PostMapper,
    private val db:PostsDb,
) : PostsRepo {
    override suspend fun fetchPosts(refresh: Boolean): Flow<Resource<List<Post>>> =
        networkBoundResource(
            query =  postsCache::posts,
            fetch =postsRemote::fetchPosts,
            saveFetchResult = {
                db.withTransaction {
                    postsCache.clear()
                    postsCache.insertAll(remoteMapper.mapModelList(it)!!)
                }
            },
            onFetchFailed = { throwable ->
                if (throwable !is HttpException && throwable !is IOException) {
                    throw throwable
                }
            },
            postExecutionThread = postExecutionThread,
            refresh = refresh,
            mapFromEntity = {
                cacheMapper.mapDomainList(it)!!
            },
            shouldFetch = {
                it.isEmpty()
            }
        )

}