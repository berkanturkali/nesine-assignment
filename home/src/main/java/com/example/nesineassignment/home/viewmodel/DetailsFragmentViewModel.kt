package com.example.nesineassignment.home.viewmodel

import androidx.lifecycle.*
import com.example.nesineassignment.core.domain.Event
import com.example.nesineassignment.core.domain.executor.abstraction.PostExecutionThread
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.core.domain.repo.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repo: PostsRepo,
    private val savedStateHandle: SavedStateHandle,
    private val postExecutionThread: PostExecutionThread
): ViewModel() {

    private val _post = MutableLiveData<Post>()

    val post: LiveData<Post> get() = _post

    private val _updated = MutableLiveData<Event<Boolean>>()
    val updated: LiveData<Event<Boolean>> get() = _updated

    init {
        savedStateHandle.get<Post>("post")?.let {
            setPost(it)
        }
    }

    private fun setPost(post:Post){
        _post.value = post
    }

    fun updateTitle(title:String){
        _post.value = _post.value?.copy(title = title)
    }

    fun updateBody(body:String){
        _post.value = _post.value?.copy(body = body)
    }

    fun updatePost(post:Post){
        viewModelScope.launch(postExecutionThread.main) {
            val id = withContext(postExecutionThread.io){repo.update(post)}
            val success = id != -1
            _updated.value = Event(success)
        }
    }
}