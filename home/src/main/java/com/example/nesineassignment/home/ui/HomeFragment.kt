package com.example.nesineassignment.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nesineassignment.common.BaseFragment
import com.example.nesineassignment.core.domain.ItemClickListener
import com.example.nesineassignment.core.domain.Resource
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.home.adapter.PostsAdapter
import com.example.nesineassignment.home.databinding.FragmentHomeBinding
import com.example.nesineassignment.home.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),ItemClickListener<Post> {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    @Inject
    lateinit var adapter: PostsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.postsRv.addItemDecoration(DividerItemDecoration(binding.postsRv.context,
            DividerItemDecoration.VERTICAL))
        binding.postsRv.apply {
            adapter = this@HomeFragment.adapter
        }

        binding.swipeRefresh.setOnRefreshListener(mViewModel::setRefresh)
        initSwipeToDelete()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.posts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    adapter.submitList(it.data)
                    showProgress(false)
                }
                is Resource.Loading -> showProgress(true)
                is Resource.Error -> {
                    // TODO: show snack
                    showProgress(false)
                }
            }
        }
    }

    private fun initSwipeToDelete(){
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val post = adapter.currentList[position]
                mViewModel.remove(post)
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.postsRv)
        }
    }

    private fun showProgress(show: Boolean) {
        binding.swipeRefresh.isRefreshing = show
    }

    override fun onItemClick(item: Post) {

    }

}