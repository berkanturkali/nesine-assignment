package com.example.nesineassignment.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    val body: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val userId: Int,
)