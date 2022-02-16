package com.example.nesineassignment.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nesineassignment.core.domain.repo.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo:PostsRepo
) :ViewModel() {

    private val shouldRefresh = MutableLiveData<Boolean>()
}