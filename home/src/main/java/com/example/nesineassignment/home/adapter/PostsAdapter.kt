package com.example.nesineassignment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.nesineassignment.core.domain.ItemClickListener
import com.example.nesineassignment.core.domain.model.Post
import com.example.nesineassignment.home.databinding.PostItemBinding
import com.example.nesineassignment.home.util.load
import javax.inject.Inject

class PostsAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable
) :
    ListAdapter<Post, PostsAdapter.PostViewHolder>(POST_COMPARATOR) {

    private lateinit var itemClickListener: ItemClickListener<Post>

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    fun setListener(itemClickListener: ItemClickListener<Post>) {
        this.itemClickListener = itemClickListener
    }

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val pos = absoluteAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = getItem(pos)
                    item?.let {
                        itemClickListener.onItemClick(it)
                    }
                }
            }
        }

        fun bind(post: Post) {
            binding.apply {
                postDescription.text = post.body
                postTitle.text = post.title
                binding.postIv.load(post.image, circularProgressDrawable = circularProgressDrawable)
            }
        }
    }
}