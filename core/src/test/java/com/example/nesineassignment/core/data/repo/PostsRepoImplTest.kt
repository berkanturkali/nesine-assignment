package com.example.nesineassignment.core.data.repo

import com.example.nesineassignment.DummyData
import com.example.nesineassignment.core.cache.mapper.PostCacheMapper
import com.example.nesineassignment.core.data.fakes.FakeErrorPostsRemote
import com.example.nesineassignment.core.data.fakes.FakePostsCache
import com.example.nesineassignment.core.data.fakes.FakePostsRemote
import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.remote.mapper.PostMapper
import com.example.nesineassignment.core.util.TestExecutionThread
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class PostsRepoImplTest {

    private val postMapper = PostMapper()

    private val executor = TestExecutionThread()

    private val postsCache = FakePostsCache()

    private val postsRemote = FakePostsRemote()

    private val cacheMapper = PostCacheMapper()

    private val postsRepo = PostsRepoImpl(
        postsRemote = postsRemote,
        remoteMapper = postMapper,
        postExecutionThread = executor,
        postsCache = postsCache,
        cacheMapper = cacheMapper
    )

    @Test
    fun `check that fetchPosts initially emits loading if refresh is true and cache is empty or null`() = runTest {
        val post = postsRepo.fetchPosts(true).first()
        Truth.assertThat(post).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun `check that fetchPosts returns error if error occurs while fetching data`() = runTest {
        val postsRepo = PostsRepoImpl(
            postsRemote = FakeErrorPostsRemote(),
            remoteMapper = postMapper,
            postExecutionThread = executor,
            postsCache = postsCache,
            cacheMapper = cacheMapper
        )
        val post = postsRepo.fetchPosts(true).toList()[1]
        Truth.assertThat(post).isInstanceOf(Resource.Error::class.java)
        Truth.assertThat((post as Resource.Error).error).isInstanceOf(IOException::class.java)
    }

    @Test
    fun `check that fetchPosts load data from cache if refresh is false`() =
        runTest {
            postsCache.upsert(DummyData.postEntity)
            val posts = postsRepo.fetchPosts(false).first()
            Truth.assertThat(posts).isInstanceOf(Resource.Success::class.java)
            val post = (posts as Resource.Success).data!!.first()
            Truth.assertThat(post).isEqualTo(DummyData.post)
        }

    @Test
    fun `check that fetchPosts load data from network if refresh is true and cache is null`() =
        runTest {
            val posts = postsRepo.fetchPosts(true).toList()[1]
            Truth.assertThat(posts).isInstanceOf(Resource.Success::class.java)
            val post = (posts as Resource.Success).data!!.first()
            Truth.assertThat(post).isEqualTo(DummyData.post)
        }

    @Test
    fun `check that fetchPosts load data from cache if refresh is true but cache is not null or empty`() =
        runTest {
            postsCache.insertAll(listOf(DummyData.postEntity))
            val posts = postsRepo.fetchPosts(true).first()
            Truth.assertThat(posts).isInstanceOf(Resource.Success::class.java)
            val post = (posts as Resource.Success).data!!.first()
            Truth.assertThat(post).isEqualTo(DummyData.post)
        }

}