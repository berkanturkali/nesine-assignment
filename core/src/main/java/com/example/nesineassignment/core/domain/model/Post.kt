package com.example.nesineassignment.core.domain.model

data class Post(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val image: String,
)