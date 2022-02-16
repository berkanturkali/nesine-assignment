package com.example.nesineassignment.home.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.nesineassignment.common.BaseFragment
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.home.databinding.FragmentDetailsBinding
import com.example.nesineassignment.home.viewmodel.DetailsFragmentViewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val mViewModel by viewModels<DetailsFragmentViewModel>()


    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var post: Post

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTitleBtn.setOnClickListener {
            showDialog(post.title, true)
        }
        binding.editBodyBtn.setOnClickListener {
            showDialog(post.body, false)
        }
        binding.updateBtn.setOnClickListener {
            mViewModel.updatePost(post)
        }
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.post.observe(viewLifecycleOwner) {
            animateVisibility(args.post != it)
            post = it
            setField(post)
        }

        mViewModel.updated.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { success ->
                if (success) findNavController().navigateUp()
            }
        }
    }

    private fun setField(post:Post){
        binding.apply {
            description.text = post.body
            title.text = post.title
        }
    }

    private fun showDialog(titleBody: String, isTitle: Boolean) {
        val action = DetailsFragmentDirections.actionDetailsFragmentToEditDialog(titleBody, isTitle)
        findNavController().navigate(action)
    }

    private fun animateVisibility(visible: Boolean) {
        val transition = Slide(Gravity.BOTTOM)
        transition.apply {
            duration = 600
            addTarget(binding.updateBtn)
        }
        TransitionManager.beginDelayedTransition(binding.rootView, transition)
        binding.updateBtn.visibility = if (visible) View.VISIBLE else View.GONE
    }
}