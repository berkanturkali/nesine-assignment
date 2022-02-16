package com.example.nesineassignment.home.viewmodel

import androidx.lifecycle.*
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: PostsRepo,
) : ViewModel() {

    private val shouldRefresh = MutableLiveData<Boolean>()

    val posts = Transformations.switchMap(shouldRefresh) {refresh->
            liveData {
                repo.fetchPosts(refresh).collect(::emit)
            }
        }

    init {
        setRefresh()
    }

    fun setRefresh() {
        shouldRefresh.value = true
    }

    fun remove(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.remove(post)
        }
    }
}