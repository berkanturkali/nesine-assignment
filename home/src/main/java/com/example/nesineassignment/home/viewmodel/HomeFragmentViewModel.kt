package com.example.nesineassignment.home.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nesineassignment.core.domain.Event
import com.example.nesineassignment.core.domain.repo.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo:PostsRepo
) :ViewModel() {

    private val shouldRefresh = MutableLiveData<Event<Boolean>>()

    val posts = Transformations.switchMap(shouldRefresh){
        it.getContentIfNotHandled()?.let { refresh->
            liveData {
                repo.fetchPosts(refresh).collect(::emit)
            }
        }
    }

    init {
        setRefresh()
    }

    fun setRefresh() {
        shouldRefresh.value = Event(true)
    }
}