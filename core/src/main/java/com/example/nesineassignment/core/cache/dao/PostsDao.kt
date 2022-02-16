package com.example.nesineassignment.core.cache.dao

import androidx.room.*
import com.example.nesineassignment.core.cache.model.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(postEntity: PostEntity): Long

    @Query("SELECT * FROM posts")
    fun posts(): Flow<List<PostEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Delete
    suspend fun delete(post: PostEntity): Int

    @Update
    suspend fun update(post: PostEntity): Int

    @Query("DELETE FROM posts")
    suspend fun clear()
}