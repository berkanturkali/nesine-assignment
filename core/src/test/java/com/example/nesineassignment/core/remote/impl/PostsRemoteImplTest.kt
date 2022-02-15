package com.example.nesineassignment.core.remote.impl

import com.example.nesineassignment.core.remote.abstraction.PostsRemote
import com.example.nesineassignment.core.remote.model.PostDto
import com.example.nesineassignment.core.util.*
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class PostsRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var postsRemote: PostsRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        postsRemote = PostsRemoteImpl(makeApiService(mockWebServer))
    }

    @Test
    fun `check that fetchPosts returns post list of same size`() = runBlocking {
        val posts: List<PostDto> = postsRemote.fetchPosts()

        val responseSize = getResponse(POSTS_RESPONSE_PATH).size

        Truth.assertThat(posts).isNotEmpty()
        Truth.assertThat(posts.size).isEqualTo(responseSize)
    }

    @Test
    fun `check that calling fetch posts make a GET request`() = runBlocking {
        postsRemote.fetchPosts()
        Truth.assertThat("GET $REQUEST_PATH HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that fetchPosts returns correct data`() {
        runBlocking {
            val response = getResponse(POSTS_RESPONSE_PATH)
            val posts = postsRemote.fetchPosts()
            Truth.assertThat(response).containsExactlyElementsIn(posts)
        }
    }


    private fun getResponse(responsePath: String) = adapter.fromJson(getJson(responsePath))!!

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}