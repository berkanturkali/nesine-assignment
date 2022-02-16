package com.example.nesineassignment.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.repo.PostsRepo
import com.example.nesineassignment.home.fakes.FakePostsRepo
import com.example.nesineassignment.home.util.CoroutineTestRule
import com.example.nesineassignment.home.util.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeFragmentViewModelTest {

    private lateinit var mViewModel: HomeFragmentViewModel

    private lateinit var repo: PostsRepo

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        repo = FakePostsRepo()
        mViewModel = HomeFragmentViewModel(repo)
    }

    @Test
    fun `check that when initialized vm returns data successfully`() = runTest {
        val posts = mViewModel.posts.getOrAwaitValue()
        Truth.assertThat(posts).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat((posts as Resource.Success).data)?.isNotEmpty()
    }
}